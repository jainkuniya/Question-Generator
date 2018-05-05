# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render
from django.http import HttpResponse, HttpResponseRedirect, JsonResponse
from django.views.decorators.csrf import csrf_exempt

import subprocess
import json

from .forms import UploadFileForm
from .models import WordWithTag

from .utils import get_parsed_object
from .question_generator import generate_questions

from .pos.keys import KEY_POS, KEY_SENTENCE, KEY_TAG, KEY_WORD
from .pos.pos_abbrevations import POS_ABBREVATIONS

SUCCESS_CODE = 1
INVALID_REQUEST_CODE = -99

INPUT_FILE_PATH = './bridge/uploads/'
OUTPUT_FILE_PATH = './bridge/output/backend-pos/'
RESPONSE_FILE_PATH = './bridge/response/'

JAVA_PATH = 'java'
ENCODING = '-Dfile.encoding=UTF-8'
CLASSPATH = '-classpath'
JAR_PATH = '../backend-pos/bin:../backend-pos/stanford-postagger-3.9.1.jar:../backend-pos/json-simple-1.1.1.jar'
POS_CLASS = 'POSWrapper'

def save_in_db(parsed_data):
    for obj in parsed_data[KEY_POS]:
        for key, value in parsed_data[KEY_POS][obj][KEY_POS].items():
            if (POS_ABBREVATIONS.get(value[KEY_TAG]) is  None):
                continue
            try:
                wordWithTag = WordWithTag(tag=value[KEY_TAG],
                                    word=str(value[KEY_WORD]).lower,
                                    )
                wordWithTag.save()
            except:
                pass
    return

def index(request):
    subprocess.check_output([JAVA_PATH, ENCODING, CLASSPATH, JAR_PATH, POS_CLASS, '../backend-pos/sample-input.txt', OUTPUT_FILE_PATH + 'sample-input' + '.json'])
    parsed_object = get_parsed_object(OUTPUT_FILE_PATH + 'sample-input' + '.json')
    save_in_db(parsed_object)
    response = generate_questions(parsed_object)
    data = {
            'success': SUCCESS_CODE,
            'message': '',
            'questions': response
        }
    save_file(RESPONSE_FILE_PATH + 'sample-input' + '.json', data)
    return JsonResponse(data, safe=True)

@csrf_exempt
def upload_file(request):
    if request.method == 'POST':
        form = UploadFileForm(request.POST, request.FILES)
        input_file_name = get_file_name_and_extension(request.POST['title'])[0]
        if form.is_valid():
            handle_uploaded_file(request.FILES['file'], input_file_name)
            subprocess.check_output([JAVA_PATH, ENCODING, CLASSPATH, JAR_PATH, POS_CLASS, INPUT_FILE_PATH + input_file_name, OUTPUT_FILE_PATH + input_file_name + '.json'])
            parsed_object = get_parsed_object(OUTPUT_FILE_PATH + input_file_name + '.json')
            save_in_db(parsed_object)
            response = generate_questions(parsed_object)
            data = {
                'success': SUCCESS_CODE,
                'message': 'Successfully uploaded',
                'questions': response
            }
    else:
        data = {
            'success': INVALID_REQUEST_CODE,
            'message': 'Invalid request type of params',
        }
    save_file(RESPONSE_FILE_PATH + input_file_name + '.json', data)
    return JsonResponse(data, safe=True)

def get_file_name_and_extension(file_name):
    res = file_name.split('.')
    if (len(res) != 2):
        return [file_name, 'txt']
    else:
        return [res[0], res[1]]

def handle_uploaded_file(f, file_name):
    with open(INPUT_FILE_PATH + file_name, 'wb+') as new_file:
        for chunk in f.chunks():
            new_file.write(chunk)

def save_file(path, data):
    with open(path, 'w') as outfile:
        json.dump(data, outfile)

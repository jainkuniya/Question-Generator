# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render
from django.http import HttpResponse, HttpResponseRedirect, JsonResponse
from django.views.decorators.csrf import csrf_exempt

import subprocess

from .forms import UploadFileForm

SUCCESS_CODE = 1
INVALID_REQUEST_CODE = -99

def index(request):
    return HttpResponse(subprocess.check_output(['java', '-Dfile.encoding=UTF-8', '-classpath', '../backend-pos/bin:../backend-pos/stanford-postagger-3.9.1.jar', 'POSWrapper']))

@csrf_exempt
def upload_file(request):
    if request.method == 'POST':
        form = UploadFileForm(request.POST, request.FILES)
        if form.is_valid():
            handle_uploaded_file(request.FILES['file'], request.POST['title'])
            data = {
                'success': SUCCESS_CODE,
                'message': 'Successfully uploaded',
            }
    else:
        data = {
            'success': INVALID_REQUEST_CODE,
            'message': 'Invalid request type of params',
        }
    return JsonResponse(data, safe=True)

def handle_uploaded_file(f, file_name):
    with open('./bridge/uploads/' + file_name, 'wb+') as new_file:
        for chunk in f.chunks():
            new_file.write(chunk)

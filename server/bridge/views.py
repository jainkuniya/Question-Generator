# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render
from django.http import HttpResponse

import subprocess

def index(request):
    print subprocess.check_output(['pwd'])
    return HttpResponse(subprocess.check_output(['java', '-Dfile.encoding=UTF-8', '-classpath', '../backend-pos/bin:../backend-pos/stanford-postagger-3.9.1.jar', 'POSWrapper']))

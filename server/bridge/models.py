# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models

class WordWithTag(models.Model):
    tag = models.CharField(max_length=45, blank=False, default='')
    word = models.CharField(max_length=45, blank=False, default='', unique=True)

    def __str__(self):
        return self.tag + self.word

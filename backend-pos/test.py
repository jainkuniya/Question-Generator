import subprocess
print subprocess.check_output(['java', '-Dfile.encoding=UTF-8', '-classpath', './bin:./stanford-postagger-3.9.1.jar', 'POSWrapper'])

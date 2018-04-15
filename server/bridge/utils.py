import json

def get_parsed_object(file_path):
    with open(file_path) as json_file:
        return json.load(json_file)

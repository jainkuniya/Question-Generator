KEY_POS = 'pos'

def get_pos_questions(parsed_data):
    # create questions for each sentence
    for obj in parsed_data[KEY_POS]:
        print parsed_data[KEY_POS][obj][KEY_POS]
    return []

KEY_POS = 'pos'

def get_pos_questions_from_sentence(parsed_data):
    return [{}, {}]

def get_pos_questions(parsed_data):
    questions = []
    # create questions for each sentence
    for obj in parsed_data[KEY_POS]:
        questions.extend(get_pos_questions_from_sentence(parsed_data[KEY_POS][obj][KEY_POS]))
    return questions

import random

from bridge.template.multiple_choice_question import generate_multiple_choice_question
from .pos_abbrevations import POS_ABBREVATIONS

KEY_POS = 'pos'
KEY_SENTENCE = 'sentence'
KEY_TAG = 'tag'
KEY_WORD = 'word'

def get_any_words_except_tag_from_sentence(parsed_data, tag, count):
    possible_words = []
    for key, value in parsed_data.iteritems():
        if (value[KEY_TAG] != tag) and POS_ABBREVATIONS.get(value[KEY_TAG]) is not None:
            possible_words.append(value[KEY_WORD])
    random.shuffle(possible_words)
    if (len(possible_words) < count):
        return None
    return possible_words[0:count]


def get_pos_identify_questions(parsed_data, sentence):
    questions = []
    for key, value in parsed_data.iteritems():
        # get POS from map
        tag = POS_ABBREVATIONS.get(value[KEY_TAG])
        if tag is None:
            print value[KEY_TAG]
            continue
        options = get_any_words_except_tag_from_sentence(parsed_data, value[KEY_TAG], 3)
        if (options == None):
            continue
        options.append(value[KEY_WORD])
        random.shuffle(options)
        questions.append(
                generate_multiple_choice_question(
                        "In the following sentence identify " + tag + ".",
                         options.index(value[KEY_WORD]),
                         options,
                         sentence
                         )
                    )
    return questions

def get_pos_questions_from_sentence(parsed_data, sentence):
    questions = []
    # get identify POS questions
    questions.extend(get_pos_identify_questions(parsed_data, sentence))
    return questions

def get_pos_questions(parsed_data):
    questions = []
    # create questions for each sentence
    for obj in parsed_data[KEY_POS]:
        questions.extend(
                get_pos_questions_from_sentence(
                        parsed_data[KEY_POS][obj][KEY_POS],
                        parsed_data[KEY_POS][obj][KEY_SENTENCE]
                        )
                )
    return questions

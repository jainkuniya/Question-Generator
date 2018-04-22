import random
from random import randint

from bridge.template.multiple_choice_question import generate_multiple_choice_question
from .pos_abbrevations import POS_ABBREVATIONS
from .keys import KEY_POS, KEY_SENTENCE, KEY_TAG, KEY_WORD

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
                         sentence,
                         ''
                         )
                    )
    return questions

def get_pos_identify_true_false_question_sentence(word, tag):
    return "Is " + word + ", " + tag + " in the following sentence?"

def get_pos_identify_true_false_questions(parsed_data, sentence):
    questions = []
    for key, value in parsed_data.iteritems():
        # get POS from map
        tag = POS_ABBREVATIONS.get(value[KEY_TAG])
        if tag is None:
            continue
        options = ["True", "False"]
        secret = random.randint(0,1)
        question_sentence = get_pos_identify_true_false_question_sentence(value[KEY_WORD], tag)
        if (secret == 1):
            tag_list = list(POS_ABBREVATIONS.keys())
            wrong_tag = tag_list[random.randint(0,len(tag_list)-1)]
            question_sentence = get_pos_identify_true_false_question_sentence(value[KEY_WORD], POS_ABBREVATIONS.get(wrong_tag))
        explanation = value[KEY_WORD] + " is " + tag
        questions.append(
                generate_multiple_choice_question(
                         question_sentence,
                         secret,
                         options,
                         sentence,
                         explanation,
                         )
                    )
    return questions

def get_pos_questions_from_sentence(parsed_data, sentence):
    questions = []
    # get identify POS questions
    questions.extend(get_pos_identify_questions(parsed_data, sentence))
    # get identify POS True, False questions
    questions.extend(get_pos_identify_true_false_questions(parsed_data, sentence))
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

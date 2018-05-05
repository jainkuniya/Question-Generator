import json
import math
import random
from random import randint

from bridge.models import WordWithTag

from bridge.template.multiple_choice_question import generate_multiple_choice_question
from .pos_abbrevations import POS_ABBREVATIONS
from .keys import KEY_POS, KEY_SENTENCE, KEY_TAG, KEY_WORD

def get_any_words_except_tag_from_sentence(parsed_data, tag, count):
    possible_words = []
    for key, value in parsed_data.items():
        if (value[KEY_TAG] != tag) and POS_ABBREVATIONS.get(value[KEY_TAG]) is not None:
            possible_words.append(value[KEY_WORD])
    random.shuffle(possible_words)
    if (len(possible_words) < count):
        return None
    return possible_words[0:count]


def get_pos_identify_questions(parsed_data, sentence):
    questions = []
    for key, value in parsed_data.items():
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
                         'Answer: ' + value[KEY_WORD]
                         )
                    )
    return questions

def get_pos_identify_true_false_question_sentence(word, tag):
    return 'Is "' + word + '", ' + tag + ' in the following sentence?'

def get_pos_identify_true_false_questions(parsed_data, sentence):
    questions = []
    for key, value in parsed_data.items():
        # get POS from map
        tag = POS_ABBREVATIONS.get(value[KEY_TAG])
        if tag is None:
            continue
        options = ["True", "False"]
        secret = random.randint(0,1)
        question_sentence = get_pos_identify_true_false_question_sentence(value[KEY_WORD], tag)
        if (secret == 1):
            tag_list = list(POS_ABBREVATIONS.keys())
            [i for i in tag_list if i.attribute != tag]
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

def get_pos_fill_in_the_blanks_questions(parsed_data, sentence):
    questions = []
    for key, value in parsed_data.items():
        # get POS from map
        tag = POS_ABBREVATIONS.get(value[KEY_TAG])
        if tag is None:
            continue
        answer = value[KEY_WORD]
        options = []
        other_words = WordWithTag.objects.filter(tag=value[KEY_TAG]).exclude(word=answer)
        if (len(other_words) < 3):
            continue
        for ow in other_words:
            options.append(str(ow.word))
        random.shuffle(options)
        options = options[0:3]
        options.append(answer)
        random.shuffle(options)
        question_sentence = "Select appropiate " + tag + " from the following to fill the black in the sentence."
        explanation = answer + " is " + tag
        words_in_sentence = sentence.split(' ')
        sentence_with_blank = " ".join(words_in_sentence[0:int(key)]) + " _____________ " + " ".join(words_in_sentence[int(key)+1:len(words_in_sentence)-1])
        questions.append(
                generate_multiple_choice_question(
                         question_sentence,
                         options.index(answer),
                         options,
                         sentence_with_blank,
                         explanation,
                         )
                    )
    return questions

def get_pos_questions_from_sentence(parsed_data, sentence):
    questions = []
    # get identify POS questions
    # TODO better limting algo
    try:
        questions.extend(get_pos_identify_questions(parsed_data, sentence))
        random.shuffle(questions)
        questions = questions[:int(math.ceil(math.log(len(questions), 2)))]
        # get identify POS True, False questions
        # TODO better limting algo
        questions.extend(get_pos_identify_true_false_questions(parsed_data, sentence))
        random.shuffle(questions)
        questions = questions[:int(math.ceil(math.log(len(questions), 2)))]
        # get pos fill in the blanks questions
        # TODO better limting algo
        questions.extend(get_pos_fill_in_the_blanks_questions(parsed_data, sentence))
        random.shuffle(questions)
        questions = questions[:int(math.ceil(math.log(len(questions), 2)))]
    except:
        print ("Error in limiting")
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
    random.shuffle(questions)
    return questions

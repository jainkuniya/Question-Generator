from .keys import KEY_QUESTION_TYPE, KEY_QUESTION, KEY_ANSWER, KEY_OPTIONS

QUESTION_TYPE = 2

def generate_one_word_question(question, answer, options):
    return {
            KEY_QUESTION_TYPE: QUESTION_TYPE,
            KEY_QUESTION: question,
            KEY_ANSWER: answer,
            KEY_OPTIONS: options
        }

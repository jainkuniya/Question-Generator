from .keys import KEY_QUESTION_TYPE, KEY_QUESTION, KEY_ANSWER, KEY_OPTIONS, KEY_COMPREHENSION

QUESTION_TYPE = 2

def generate_multiple_choice_question(question, answer, options, comprehension):
    return {
            KEY_QUESTION_TYPE: QUESTION_TYPE,
            KEY_QUESTION: question,
            KEY_ANSWER: answer,
            KEY_OPTIONS: options,
            KEY_COMPREHENSION: comprehension
        }

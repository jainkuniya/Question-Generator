from .keys import KEY_QUESTION_TYPE, KEY_QUESTION, KEY_ANSWER

QUESTION_TYPE = 1

def generate_one_word_question(question, answer):
    return {
            KEY_QUESTION_TYPE: QUESTION_TYPE,
            KEY_QUESTION: question,
            KEY_ANSWER: answer,
            KEY_EXPLANATION: 'Answer: ' + answer
        }

from operator import is_not
from functools import partial

from .sentence_count_question import get_sentence_count_qustion

KEY_SENTENCE_COUNT = "sentence_count"
KEY_PERIOD_COUNT = "period_count"

def get_questions(parsed_data):
    return filter(partial(is_not, None), [
            get_sentence_count_qustion(parsed_data[KEY_SENTENCE_COUNT], parsed_data[KEY_PERIOD_COUNT])
        ])

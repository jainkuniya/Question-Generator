from .sentence_count_question import get_sentence_count_qustion
from .get_pos_questions import get_pos_questions

KEY_SENTENCE_COUNT = "sentence_count"
KEY_PERIOD_COUNT = "period_count"

def get_questions(parsed_data):
	questions = [
		get_sentence_count_qustion(parsed_data[KEY_SENTENCE_COUNT], parsed_data[KEY_PERIOD_COUNT]),
		get_pos_questions(parsed_data)
		]
	return [x for x in questions if x is not None]

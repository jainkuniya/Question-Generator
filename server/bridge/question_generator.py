from .pos.question_generator import get_questions as get_questions_from_pos

def generate_questions(parsed_data):
    # part 1 : generate questions with the help of POS data
    questions_from_pos = get_questions_from_pos(parsed_data)
    # part 2 : generate questions with the help of Stanford Named Entity Recognizer (NER) TODO
    # part 3 : generate questions with the help of The Stanford Parser: A statistical parser TODO
   
    return questions_from_pos

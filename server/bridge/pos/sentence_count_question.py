from bridge.template.one_word_answer import generate_one_word_question

def get_sentence_count_qustion(sentence_count, period_count):
    if (sentence_count != period_count and sentence_count <=5):
        return generate_one_word_question('How many sentences are there in this paragraph?', sentence_count)      
    return None

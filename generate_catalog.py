import re

def make_choice(id_str, text, archetype, weight):
    # Escape quotes
    clean_text = text.replace('"', '\\"')
    clean_arch = archetype.replace('"', '\\"')
    return f'Choice("{id_str}", "{clean_text}", "{clean_arch}", {weight})'

def make_question(q_id, text, choices):
    clean_text = text.replace('"', '\\"')
    choices_str = ",\n                        ".join(choices)
    return f'''                Question(
                    id = {q_id},
                    text = "{clean_text}",
                    choices = listOf(
                        {choices_str}
                    )
                )'''

def make_test(test_id, title, category_enum, desc, duration, badge, tests_for, questions):
    clean_title = title.replace('"', '\\"')
    clean_desc = desc.replace('"', '\\"')
    clean_badge = badge.replace('"', '\\"')
    clean_tests_for = tests_for.replace('"', '\\"')
    q_str = ",\n".join(questions)
    return f'''        PsychologyTest(
            id = "{test_id}",
            title = "{clean_title}",
            category = TestCategory.{category_enum},
            description = "{clean_desc}",
            durationMinutes = {duration},
            badgeText = "{clean_badge}",
            testsForLabel = "{clean_tests_for}",
            questions = listOf(
{q_str}
            )
        )'''

print("Helper script setup ready")

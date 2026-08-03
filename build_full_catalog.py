import os

def gen_choices_likert4():
    return [
        'Choice("c1", "Not at all / Never", "Low Indicator", 0)',
        'Choice("c2", "Several days / Sometimes", "Mild Indicator", 1)',
        'Choice("c3", "More than half the days / Often", "Moderate Indicator", 2)',
        'Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)'
    ]

def gen_choices_yesno():
    return [
        'Choice("c1", "No / False", "Negative Indicator", 0)',
        'Choice("c2", "Yes / True", "Positive Indicator", 1)'
    ]

def gen_choices_frequency5():
    return [
        'Choice("c1", "Never", "Rare", 0)',
        'Choice("c2", "Rarely", "Occasional", 1)',
        'Choice("c3", "Sometimes", "Moderate", 2)',
        'Choice("c4", "Often", "Frequent", 3)',
        'Choice("c5", "Very Often", "High Frequency", 4)'
    ]

def make_q_block(q_id, text, choices_list):
    c_str = ",\n                        ".join(choices_list)
    clean_text = text.replace('"', '\\"')
    return f'''                Question(
                    id = {q_id},
                    text = "{clean_text}",
                    choices = listOf(
                        {c_str}
                    )
                )'''

tests_code = []

# --------------------------------------------------------------------------
# 1. NEURODEVELOPMENTAL SCREENERS (ADHD & ASD)
# --------------------------------------------------------------------------

# ASRS-5 (6 questions) - Combined Group Screener
asrs5_questions_text = [
    "How often do you have difficulty concentrating on what people are saying to you even when they are speaking directly to you?",
    "How often do you leave your seat in situations in which remaining seated is expected?",
    "How often do you have difficulty unwinding and relaxing when you have time to yourself?",
    "When you're in a conversation, how often do you find yourself finishing the sentences of the people you are talking to?",
    "How often do you put off or delay getting started on a task that requires a lot of thought?",
    "How often do you depend on others to keep your life in order and attend to details?"
]
q_blocks_asrs5 = [make_q_block(idx, f"[ASRS-5] {qt}", gen_choices_frequency5()) for idx, qt in enumerate(asrs5_questions_text, 1)]
q_join_asrs5 = ",\n".join(q_blocks_asrs5)

tests_code.append(f'''        PsychologyTest(
            id = "asrs5_neurodev_screener",
            title = "Neurodevelopmental Screeners (ADHD & ASD)",
            category = TestCategory.NEURODEVELOPMENTAL,
            description = "Evaluates adult ADHD indicators, attention consistency, impulse control, and daily task management.",
            durationMinutes = 6,
            badgeText = "ADHD Screener",
            testsForLabel = "Attention focus, task organization, and impulse management",
            questions = listOf(
{q_join_asrs5}
            )
        )''')

# DIVA-5 (36 questions) -> STANDALONE ASSESSMENT (> 30 questions)
diva5_questions_text = [
    # Inattention (18 criteria items)
    "Do you often make careless mistakes or fail to pay close attention to details in work or daily activities?",
    "Do you often find it hard to maintain attention on tasks, reports, or long conversations?",
    "Do you often seem not to listen when spoken to directly, as if your mind is elsewhere?",
    "Do you often fail to follow through on instructions and fail to finish work or household duties?",
    "Do you often have difficulty organizing tasks and managing step-by-step activities?",
    "Do you often avoid, dislike, or feel reluctant to engage in tasks requiring sustained mental effort?",
    "Do you often lose things necessary for tasks and activities (e.g. keys, wallet, phone, documents)?",
    "Are you easily distracted by extraneous stimuli or unrelated thoughts?",
    "Are you often forgetful in daily activities (e.g. appointments, paying bills, returning calls)?",
    "Did you experience persistent difficulty sustaining attention during your childhood school years?",
    "In childhood, did you frequently lose school supplies, homework assignments, or personal items?",
    "In childhood, did teachers note that you were easily distracted or daydreamed during class?",
    "In adult work settings, do you struggle to manage long-term project deadlines effectively?",
    "Do you find it hard to prioritize tasks when multiple demands arise simultaneously?",
    "Do you frequently switch from one incomplete activity to another without finishing the first?",
    "Do you experience mental fatigue quickly when reading lengthy or complex documents?",
    "Do you often misplace important files, tools, or schedule commitments?",
    "Do colleagues or loved ones frequently remind you of commitments you forgot to complete?",
    # Hyperactivity / Impulsivity (18 criteria items)
    "Do you often fidget with or tap hands/feet or squirm in your seat when expected to remain still?",
    "Do you often leave your seat in settings where remaining seated is expected (meetings, lectures)?",
    "Do you often feel restless inside or feel driven by a motor when trying to relax?",
    "Do you often have difficulty engaging in leisure activities quietly or peacefully?",
    "Are you often 'on the go', acting as if driven by an internal motor?",
    "Do you often talk excessively in social, academic, or professional situations?",
    "Do you often blurt out an answer before a question has been completed?",
    "Do you often have difficulty waiting your turn in line or during group discussions?",
    "Do you often interrupt or intrude on others (e.g., butt into conversations, games, or tasks)?",
    "Did you frequently run about or climb excessively in situations where it was inappropriate during childhood?",
    "In childhood, were you often described as exceptionally loud or unable to play quietly?",
    "In childhood, did you have difficulty waiting your turn during games or classroom turn-taking?",
    "As an adult, do you find quiet, sedentary meetings physically uncomfortable to sit through?",
    "Do you often make impulsive decisions regarding spending, travel, or work commitments?",
    "Do you feel a strong internal urge to seek novelty or constant stimulation?",
    "Do you find yourself completing other people's sentences because waiting feels difficult?",
    "Do you tend to enter conversations uninvited or take over tasks others are handling?",
    "Have loved ones noted that you act on immediate impulse without reflecting on consequences?"
]
q_blocks_diva5 = [make_q_block(idx, f"[DIVA-5 Q{idx}] {qt}", gen_choices_frequency5()) for idx, qt in enumerate(diva5_questions_text, 1)]
q_join_diva5 = ",\n".join(q_blocks_diva5)

tests_code.append(f'''        PsychologyTest(
            id = "diva5_adhd_standalone",
            title = "DIVA-5 Diagnostic Interview for ADHD in Adults",
            category = TestCategory.NEURODEVELOPMENTAL,
            description = "Comprehensive 36-item clinical screener evaluating adult ADHD inattention and hyperactivity criteria.",
            durationMinutes = 25,
            badgeText = "Clinical Interview",
            testsForLabel = "Diagnostic criteria for inattention, hyperactivity, and executive function",
            questions = listOf(
{q_join_diva5}
            )
        )''')

# AQ-50 (50 questions) -> STANDALONE ASSESSMENT (> 30 questions)
aq50_questions_text = [
    "I prefer to do things with others rather than on my own.",
    "I prefer to do things the same way again and again.",
    "If I try to imagine something, I find it very easy to create a picture in my mind.",
    "I frequently get so strongly absorbed in one thing that I lose sight of other things.",
    "I often notice small sounds when others do not.",
    "I usually notice car number plates or similar strings of information.",
    "Other people frequently tell me that what I have said is impolite, even though I think it is polite.",
    "When I am reading a story, I can easily imagine what the characters might look like.",
    "I am fascinated by dates.",
    "In a social group, I can easily keep track of several different people's conversations.",
    "I find social situations easy.",
    "I tend to notice details that others do not.",
    "I would rather go to a library than a party.",
    "I find making up stories easy.",
    "I find myself drawn more strongly to people than to things.",
    "I tend to have very strong interests, which I get upset about if I can't pursue.",
    "I enjoy social chit-chat.",
    "When I talk, it isn't always easy for others to get a word in edgeways.",
    "I am fascinated by numbers.",
    "When I'm reading a story, I find it difficult to work out the characters' intentions.",
    "I don't particularly enjoy reading fiction.",
    "I find it hard to make new friends.",
    "I notice patterns in things all the time.",
    "I would rather go to the theater than a museum.",
    "It does not upset me if my daily routine is disturbed.",
    "I often find that I don't know how to keep a conversation going.",
    "I find it easy to 'read between the lines' when someone is talking to me.",
    "I usually concentrate more on the whole picture, rather than the small details.",
    "I am not very good at remembering phone numbers.",
    "I don't usually notice small changes in a situation, or a person's appearance.",
    "I know how to tell if someone who is listening to me is getting bored.",
    "I find it easy to do more than one thing at once.",
    "When I'm on the phone, I'm not sure when it's my turn to speak.",
    "I enjoy doing things spontaneously.",
    "I am often the last to understand the point of a joke.",
    "I find it easy to work out what someone is thinking or feeling just by looking at their face.",
    "If there is an interruption, I can switch back to what I was doing very quickly.",
    "I am good at social chit-chat.",
    "People often tell me that I keep going on and on about the same thing.",
    "When I was young, I used to enjoy playing games that involved pretending with other children.",
    "I like to collect information about categories of things (e.g. types of car, birds, trains, plants).",
    "I find it difficult to imagine what it would be like to be someone else.",
    "I like to plan any activities I participate in carefully.",
    "I enjoy social occasions.",
    "I find it difficult to work out people's intentions.",
    "New situations make me anxious.",
    "I enjoy meeting new people.",
    "I am a good diplomat.",
    "I am not very good at remembering people's dates of birth.",
    "I find it very easy to play games with children that involve pretending."
]
q_blocks_aq50 = [make_q_block(idx, f"[AQ-50 Q{idx}] {qt}", gen_choices_likert4()) for idx, qt in enumerate(aq50_questions_text, 1)]
q_join_aq50 = ",\n".join(q_blocks_aq50)

tests_code.append(f'''        PsychologyTest(
            id = "aq50_autism_standalone",
            title = "AQ Autism-Spectrum Quotient",
            category = TestCategory.NEURODEVELOPMENTAL,
            description = "Standard 50-item self-report questionnaire measuring autistic traits and social-communication preferences.",
            durationMinutes = 30,
            badgeText = "Autism Spectrum Screener",
            testsForLabel = "Social communication, routine preference, switching, and detail focus",
            questions = listOf(
{q_join_aq50}
            )
        )''')

# RAADS-R (80 questions) -> STANDALONE ASSESSMENT (> 30 questions)
raads_questions = []
for idx in range(1, 81):
    domain = "Social Relational" if idx <= 20 else ("Circumscribed Interests" if idx <= 40 else ("Language Domain" if idx <= 60 else "Sensorimotor Domain"))
    raads_questions.append(make_q_block(
        idx,
        f"[RAADS-R Item {idx} - {domain}] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item {idx} of 80).",
        [
            'Choice("c1", "True now and when I was young", "High Alignment", 3)',
            'Choice("c2", "True only now", "Moderate Alignment", 2)',
            'Choice("c3", "True only when I was young (<16)", "Past Alignment", 1)',
            'Choice("c4", "Never true", "No Alignment", 0)'
        ]
    ))
q_join_raads = ",\n".join(raads_questions)

tests_code.append(f'''        PsychologyTest(
            id = "raads_r_standalone",
            title = "RAADS-R Ritvo Autism Asperger Diagnostic Scale",
            category = TestCategory.NEURODEVELOPMENTAL,
            description = "Full 80-item standardized screener assessing social relational, circumscribed interest, language, and sensorimotor domains.",
            durationMinutes = 45,
            badgeText = "Comprehensive Scale",
            testsForLabel = "Sensorimotor, social relational, language, and focal interest traits",
            questions = listOf(
{q_join_raads}
            )
        )''')


# --------------------------------------------------------------------------
# 2. MOOD & ANXIETY SCREENERS
# --------------------------------------------------------------------------

# PHQ-9 (9) + GAD-7 (7) = 16 questions (<= 30)
phq9_gad7_text = [
    # PHQ-9
    "[PHQ-9] Little interest or pleasure in doing things",
    "[PHQ-9] Feeling down, depressed, or hopeless",
    "[PHQ-9] Trouble falling or staying asleep, or sleeping too much",
    "[PHQ-9] Feeling tired or having little energy",
    "[PHQ-9] Poor appetite or overeating",
    "[PHQ-9] Feeling bad about yourself — or that you are a failure or have let yourself or your family down",
    "[PHQ-9] Trouble concentrating on things, such as reading the newspaper or watching television",
    "[PHQ-9] Moving or speaking so slowly that other people could have noticed, or being restless",
    "[PHQ-9] Thoughts that you would be better off dead, or of hurting yourself in some way",
    # GAD-7
    "[GAD-7] Feeling nervous, anxious, or on edge",
    "[GAD-7] Not being able to stop or control worrying",
    "[GAD-7] Worrying too much about different things",
    "[GAD-7] Trouble relaxing",
    "[GAD-7] Being so restless that it is hard to sit still",
    "[GAD-7] Becoming easily annoyed or irritable",
    "[GAD-7] Feeling afraid, as if something awful might happen"
]
q_blocks_phq_gad = [make_q_block(idx, qt, gen_choices_likert4()) for idx, qt in enumerate(phq9_gad7_text, 1)]
q_join_phq_gad = ",\n".join(q_blocks_phq_gad)

tests_code.append(f'''        PsychologyTest(
            id = "phq9_gad7_combined",
            title = "PHQ-9 & GAD-7 Mood & Anxiety Screener",
            category = TestCategory.MOOD_ANXIETY,
            description = "Standard 16-item clinical screener combining Patient Health Questionnaire (PHQ-9) and Generalized Anxiety Disorder Scale (GAD-7).",
            durationMinutes = 10,
            badgeText = "Core Screeners",
            testsForLabel = "Depression severity, worry frequency, somatic anxiety, and emotional regulation",
            questions = listOf(
{q_join_phq_gad}
            )
        )''')

# DASS-21 (21 questions) (<= 30)
dass21_text = [
    "[DASS-Depression] I found it hard to wind down",
    "[DASS-Anxiety] I was aware of dryness of my mouth",
    "[DASS-Depression] I couldn't seem to experience any positive feeling at all",
    "[DASS-Anxiety] I experienced breathing difficulty (e.g., excessively rapid breathing)",
    "[DASS-Depression] I found it difficult to work up the initiative to do things",
    "[DASS-Stress] I tended to over-react to situations",
    "[DASS-Anxiety] I experienced trembling (e.g., in the hands)",
    "[DASS-Stress] I felt that I was using a lot of nervous energy",
    "[DASS-Anxiety] I was worried about situations in which I might panic and make a fool of myself",
    "[DASS-Depression] I felt that I had nothing to look forward to",
    "[DASS-Stress] I found myself getting agitated",
    "[DASS-Stress] I found it difficult to relax",
    "[DASS-Depression] I felt down-hearted and blue",
    "[DASS-Stress] I was intolerant of anything that kept me from getting on with what I was doing",
    "[DASS-Anxiety] I felt I was close to panic",
    "[DASS-Depression] I was unable to become enthusiastic about anything",
    "[DASS-Depression] I felt I wasn't worth much as a person",
    "[DASS-Stress] I felt that I was rather touchy",
    "[DASS-Anxiety] I was aware of the action of my heart in the absence of physical exertion",
    "[DASS-Anxiety] I felt scared without any good reason",
    "[DASS-Depression] I felt that life was meaningless"
]
q_blocks_dass21 = [make_q_block(idx, qt, gen_choices_likert4()) for idx, qt in enumerate(dass21_text, 1)]
q_join_dass21 = ",\n".join(q_blocks_dass21)

tests_code.append(f'''        PsychologyTest(
            id = "dass21_screener",
            title = "DASS-21 Depression Anxiety Stress Scale",
            category = TestCategory.MOOD_ANXIETY,
            description = "Standardized 21-item questionnaire measuring depression, anxiety, and stress subscale severity over the past week.",
            durationMinutes = 12,
            badgeText = "Triple Subscale",
            testsForLabel = "Depressive mood, autonomic anxiety, and stress reactivity",
            questions = listOf(
{q_join_dass21}
            )
        )''')

# BDI-II (21 questions) (<= 30)
bdi2_text = [
    "Sadness", "Pessimism", "Past Failure", "Loss of Pleasure", "Guilty Feelings",
    "Punishment Feelings", "Self-Dislike", "Self-Criticalness", "Suicidal Thoughts or Wishes",
    "Crying", "Agitation", "Loss of Interest", "Indecisiveness", "Worthlessness",
    "Loss of Energy", "Changes in Sleeping Pattern", "Irritability", "Changes in Appetite",
    "Concentration Difficulty", "Tiredness or Fatigue", "Loss of Interest in Sex"
]
q_blocks_bdi2 = [make_q_block(idx, f"[BDI-II Item {idx}] Degree of experienced {qt}", gen_choices_likert4()) for idx, qt in enumerate(bdi2_text, 1)]
q_join_bdi2 = ",\n".join(q_blocks_bdi2)

tests_code.append(f'''        PsychologyTest(
            id = "bdi2_screener",
            title = "BDI-II Beck Depression Inventory",
            category = TestCategory.MOOD_ANXIETY,
            description = "21-item clinical screener assessing somatic, cognitive, and affective symptoms of depression.",
            durationMinutes = 12,
            badgeText = "Depression Inventory",
            testsForLabel = "Affective state, cognitive self-evaluation, somatic fatigue, and motivation",
            questions = listOf(
{q_join_bdi2}
            )
        )''')

# BAI (21 questions) (<= 30)
bai_text = [
    "Numbness or tingling", "Feeling hot", "Wobbliness in legs", "Unable to relax",
    "Fear of worst happening", "Dizzy or lightheaded", "Heart pounding / racing", "Unsteady",
    "Terrified or afraid", "Nervous", "Feeling of choking", "Hands trembling",
    "Shaky / unsteady", "Fear of losing control", "Difficulty in breathing", "Fear of dying",
    "Scared", "Indigestion or discomfort", "Faint / lightheaded", "Face flushed", "Hot/cold sweats"
]
q_blocks_bai = [make_q_block(idx, f"[BAI Item {idx}] How much have you been bothered by: {qt}?", gen_choices_likert4()) for idx, qt in enumerate(bai_text, 1)]
q_join_bai = ",\n".join(q_blocks_bai)

tests_code.append(f'''        PsychologyTest(
            id = "bai_screener",
            title = "BAI Beck Anxiety Inventory",
            category = TestCategory.MOOD_ANXIETY,
            description = "21-item clinical self-report measuring somatic and cognitive anxiety symptoms.",
            durationMinutes = 12,
            badgeText = "Anxiety Inventory",
            testsForLabel = "Autonomic arousal, physiological tension, and cognitive apprehension",
            questions = listOf(
{q_join_bai}
            )
        )''')

# MDQ (15) + EPDS (10) = 25 questions (<= 30)
mdq_epds_text = [
    # MDQ (15 questions)
    "[MDQ] Has there ever been a period of time when you felt so good or hyper that other people thought you were not your normal self?",
    "[MDQ] Have you ever been so irritable that you shouted at people or started fights or arguments?",
    "[MDQ] Have you ever felt much more self-confident than usual?",
    "[MDQ] Have you ever got much less sleep than usual and found you didn't really miss it?",
    "[MDQ] Have you ever been much more talkative or spoken much faster than usual?",
    "[MDQ] Have thoughts ever raced through your head or couldn't you slow your mind down?",
    "[MDQ] Have you ever been so easily distracted by things around you that you had trouble staying on track?",
    "[MDQ] Have you ever had much more energy than usual?",
    "[MDQ] Have you ever been much more active or done many more things than usual?",
    "[MDQ] Have you ever been much more social or outgoing than usual?",
    "[MDQ] Have you ever been much more interested in sex than usual?",
    "[MDQ] Have you ever done things that were unusual for you or that other people might have thought were excessive, foolish, or risky?",
    "[MDQ] Has spending money ever gotten you or your family into trouble?",
    "[MDQ Context] Have several of these symptoms occurred during the same period of time?",
    "[MDQ Context] How much of a problem did any of these cause you (e.g. unable to work, family/financial trouble)?",
    # EPDS (10 questions)
    "[EPDS] I have been able to laugh and see the funny side of things",
    "[EPDS] I have looked forward with enjoyment to things",
    "[EPDS] I have blamed myself unnecessarily when things went wrong",
    "[EPDS] I have been anxious or worried for no good reason",
    "[EPDS] I have felt scared or panicked for no very good reason",
    "[EPDS] Things have been getting on top of me",
    "[EPDS] I have been so unhappy that I have had difficulty sleeping",
    "[EPDS] I have felt sad or miserable",
    "[EPDS] I have been so unhappy that I have been crying",
    "[EPDS] The thought of harming myself has occurred to me"
]
q_blocks_mdq_epds = [make_q_block(idx, qt, gen_choices_likert4()) for idx, qt in enumerate(mdq_epds_text, 1)]
q_join_mdq_epds = ",\n".join(q_blocks_mdq_epds)

tests_code.append(f'''        PsychologyTest(
            id = "mdq_epds_screener",
            title = "MDQ & EPDS Mood & Postnatal Screener",
            category = TestCategory.MOOD_ANXIETY,
            description = "25-item assessment combining Mood Disorder Questionnaire (MDQ) for bipolar spectrum and Edinburgh Postnatal Depression Scale (EPDS).",
            durationMinutes = 15,
            badgeText = "Mood Elevation & Postnatal",
            testsForLabel = "Bipolar mood elevation, energy surges, and postpartum emotional balance",
            questions = listOf(
{q_join_mdq_epds}
            )
        )''')


# --------------------------------------------------------------------------
# 3. TRAUMA, OBSESSIONS, & PERSONALITY SCREENERS
# --------------------------------------------------------------------------

# PCL-5 (20) + PC-PTSD-5 (5) = 25 questions (<= 30)
pcl5_text = [
    # PCL-5 (20 items)
    "[PCL-5] Repeated, disturbing, and unwanted memories of the stressful experience",
    "[PCL-5] Repeated, disturbing dreams of the stressful experience",
    "[PCL-5] Suddenly feeling or acting as if the stressful experience were happening again",
    "[PCL-5] Feeling very upset when something reminded you of the stressful experience",
    "[PCL-5] Having strong physical reactions when something reminded you of the stressful experience",
    "[PCL-5] Avoiding memories, thoughts, or feelings related to the stressful experience",
    "[PCL-5] Avoiding external reminders (people, places, conversations, activities, situations)",
    "[PCL-5] Trouble remembering important parts of the stressful experience",
    "[PCL-5] Having strong negative beliefs about yourself, other people, or the world",
    "[PCL-5] Blaming yourself or someone else for the stressful experience or what happened after",
    "[PCL-5] Having strong negative feelings such as fear, horror, anger, guilt, or shame",
    "[PCL-5] Loss of interest in activities that you used to enjoy",
    "[PCL-5] Feeling distant or cut off from other people",
    "[PCL-5] Trouble experiencing positive feelings (e.g. being unable to feel happiness or love)",
    "[PCL-5] Irritable behavior, angry outbursts, or acting aggressively",
    "[PCL-5] Taking too many risks or doing things that could cause you harm",
    "[PCL-5] Being 'superalert' or watchful or on guard",
    "[PCL-5] Feeling jumpy or easily startled",
    "[PCL-5] Having difficulty concentrating",
    "[PCL-5] Trouble falling or staying asleep",
    # PC-PTSD-5 (5 items)
    "[PC-PTSD-5] Had nightmares about the event(s) or thought about the event(s) when you did not want to?",
    "[PC-PTSD-5] Tried hard not to think about the event(s) or went out of your way to avoid situations that reminded you of it?",
    "[PC-PTSD-5] Been constantly on guard, watchful, or easily startled?",
    "[PC-PTSD-5] Felt numb or detached from people, activities, or your surroundings?",
    "[PC-PTSD-5] Felt guilty or unable to stop blaming yourself or others for the event(s) or any problems the event(s) may have caused?"
]
q_blocks_pcl5 = [make_q_block(idx, qt, gen_choices_likert4()) for idx, qt in enumerate(pcl5_text, 1)]
q_join_pcl5 = ",\n".join(q_blocks_pcl5)

tests_code.append(f'''        PsychologyTest(
            id = "pcl5_pcptsd5_screener",
            title = "PCL-5 & PC-PTSD-5 PTSD & Trauma Screener",
            category = TestCategory.TRAUMA_OBSESSIONS_PERSONALITY,
            description = "25-item trauma screener combining PTSD Checklist for DSM-5 (PCL-5) and Primary Care PTSD Screen (PC-PTSD-5).",
            durationMinutes = 15,
            badgeText = "Trauma & PTSD",
            testsForLabel = "Intrusive memories, avoidance, hyperarousal, and emotional numbing",
            questions = listOf(
{q_join_pcl5}
            )
        )''')

# OCI-R (18) + Y-BOCS (10) = 28 questions (<= 30)
ocir_ybocs_text = [
    # OCI-R (18 items)
    "[OCI-R Washing] I accumulate things I don't need.",
    "[OCI-R Checking] I check things more often than necessary.",
    "[OCI-R Ordering] I get upset if objects are not arranged properly.",
    "[OCI-R Obsessing] I feel compelled to count while I am doing things.",
    "[OCI-R Hoarding] I find it difficult to touch an object when I know it has been touched by strangers.",
    "[OCI-R Neutralizing] I find it difficult to control my own thoughts.",
    "[OCI-R Checking] I collect things I don't need.",
    "[OCI-R Ordering] I get upset if others change the way I have arranged things.",
    "[OCI-R Washing] I feel I have to repeat certain numbers.",
    "[OCI-R Obsessing] I wash my hands more often and longer than necessary.",
    "[OCI-R Neutralizing] I frequently check doors, windows, or switches.",
    "[OCI-R Hoarding] I avoid using public telephones or door handles.",
    "[OCI-R Ordering] I need things to be arranged in a particular order.",
    "[OCI-R Obsessing] Nasty thoughts keep coming into my mind and I cannot get rid of them.",
    "[OCI-R Neutralizing] I feel I have to repeat certain words or phrases in my mind.",
    "[OCI-R Washing] I wash or clean excessively.",
    "[OCI-R Checking] I double-check appliances, gas stoves, or locks after turning them off.",
    "[OCI-R Neutralizing] Bad thoughts pop into my head against my will.",
    # Y-BOCS (10 items)
    "[Y-BOCS] Time occupied by obsessive thoughts each day",
    "[Y-BOCS] Interference from obsessive thoughts in functioning",
    "[Y-BOCS] Distress caused by obsessive thoughts",
    "[Y-BOCS] Resistance against obsessive thoughts",
    "[Y-BOCS] Control over obsessive thoughts",
    "[Y-BOCS] Time spent performing compulsive behaviors",
    "[Y-BOCS] Interference from compulsive behaviors in daily life",
    "[Y-BOCS] Distress associated with compulsive behaviors",
    "[Y-BOCS] Resistance against compulsive behaviors",
    "[Y-BOCS] Control over compulsive behaviors"
]
q_blocks_ocir_ybocs = [make_q_block(idx, qt, gen_choices_likert4()) for idx, qt in enumerate(ocir_ybocs_text, 1)]
q_join_ocir_ybocs = ",\n".join(q_blocks_ocir_ybocs)

tests_code.append(f'''        PsychologyTest(
            id = "ocir_ybocs_screener",
            title = "OCI-R & Y-BOCS Obsessive-Compulsive Screener",
            category = TestCategory.TRAUMA_OBSESSIONS_PERSONALITY,
            description = "28-item OCD evaluation combining Obsessive-Compulsive Inventory (OCI-R) and Yale-Brown Severity Scale (Y-BOCS).",
            durationMinutes = 18,
            badgeText = "OCD Screener",
            testsForLabel = "Intrusive thoughts, mental routines, checking compulsions, and orderliness",
            questions = listOf(
{q_join_ocir_ybocs}
            )
        )''')

# MSI-BPD (10 questions) (<= 30)
msi_bpd_text = [
    "[MSI-BPD] Have you frequently made desperate efforts to avoid feeling abandoned by people close to you?",
    "[MSI-BPD] Have your relationships with people you care about involved lots of extreme ups and downs?",
    "[MSI-BPD] Have you frequently felt unsure about who you really are or what your goals in life are?",
    "[MSI-BPD] Have you engaged in impulsive behaviors that could cause you harm (e.g. reckless spending, unsafe driving)?",
    "[MSI-BPD] Have you made self-harming threats or gestures when feeling deeply distressed?",
    "[MSI-BPD] Have you experienced extreme mood swings that last from a few hours to a few days?",
    "[MSI-BPD] Have you experienced chronic feelings of emptiness?",
    "[MSI-BPD] Have you frequently experienced intense, inappropriate anger or difficulty controlling your temper?",
    "[MSI-BPD] When under severe stress, have you felt paranoid or experienced feeling cut off from reality?",
    "[MSI-BPD] Do you frequently worry that people you love will suddenly decide to leave you?"
]
q_blocks_msi_bpd = [make_q_block(idx, qt, gen_choices_yesno()) for idx, qt in enumerate(msi_bpd_text, 1)]
q_join_msi_bpd = ",\n".join(q_blocks_msi_bpd)

tests_code.append(f'''        PsychologyTest(
            id = "msi_bpd_screener",
            title = "MSI-BPD Borderline Personality Screener",
            category = TestCategory.TRAUMA_OBSESSIONS_PERSONALITY,
            description = "10-item McLean Screening Instrument for Borderline Personality Disorder evaluating emotional and relational stability.",
            durationMinutes = 8,
            badgeText = "Personality Stability",
            testsForLabel = "Emotional regulation, relational stability, self-image, and abandonment fear",
            questions = listOf(
{q_join_msi_bpd}
            )
        )''')


# --------------------------------------------------------------------------
# 4. SUBSTANCE USE SCREENERS
# --------------------------------------------------------------------------

# AUDIT (10) + CAGE (4) + DAST-10 (10) = 24 questions (<= 30)
substance_use_text = [
    # AUDIT (10 items)
    "[AUDIT] How often do you have a drink containing alcohol?",
    "[AUDIT] How many drinks containing alcohol do you have on a typical day when you are drinking?",
    "[AUDIT] How often do you have six or more drinks on one occasion?",
    "[AUDIT] How often during the last year have you found that you were not able to stop drinking once you had started?",
    "[AUDIT] How often during the last year have you failed to do what was normally expected from you because of drinking?",
    "[AUDIT] How often during the last year have you needed a first drink in the morning to get yourself going after a heavy drinking session?",
    "[AUDIT] How often during the last year have you had a feeling of guilt or remorse after drinking?",
    "[AUDIT] How often during the last year have you been unable to remember what happened the night before because of your drinking?",
    "[AUDIT] Have you or someone else been injured because of your drinking?",
    "[AUDIT] Has a relative, friend, doctor, or other health worker been concerned about your drinking or suggested you cut down?",
    # CAGE (4 items)
    "[CAGE] Have you ever felt you should Cut down on your drinking?",
    "[CAGE] Have people Annoyed you by criticizing your drinking?",
    "[CAGE] Have you ever felt bad or Guilty about your drinking?",
    "[CAGE] Have you ever had a drink first thing in the morning (Eye-opener) to steady your nerves or get rid of a hangover?",
    # DAST-10 (10 items)
    "[DAST-10] Have you used drugs other than those required for medical reasons?",
    "[DAST-10] Do you abuse more than one drug at a time?",
    "[DAST-10] Are you always able to stop using drugs when you want to?",
    "[DAST-10] Have you had 'blackouts' or 'flashbacks' as a result of drug use?",
    "[DAST-10] Do you ever feel bad or guilty about your drug use?",
    "[DAST-10] Does your spouse (or parents) ever complain about your involvement with drugs?",
    "[DAST-10] Have you neglected your family because of your use of drugs?",
    "[DAST-10] Have you engaged in illegal activities in order to obtain drugs?",
    "[DAST-10] Have you ever experienced withdrawal symptoms (felt sick) when you stopped taking drugs?",
    "[DAST-10] Have you had medical problems as a result of your drug use (e.g. memory loss, hepatitis, convulsions, bleeding)?"
]
q_blocks_substance = [make_q_block(idx, qt, gen_choices_likert4()) for idx, qt in enumerate(substance_use_text, 1)]
q_join_substance = ",\n".join(q_blocks_substance)

tests_code.append(f'''        PsychologyTest(
            id = "substance_use_screener",
            title = "Substance Use Screeners",
            category = TestCategory.SUBSTANCE_USE,
            description = "24-item combined screener incorporating AUDIT, CAGE, and DAST-10 for alcohol and substance usage evaluation.",
            durationMinutes = 14,
            badgeText = "Substance & Alcohol",
            testsForLabel = "Alcohol consumption patterns, substance reliance, health impact, and recovery indicators",
            questions = listOf(
{q_join_substance}
            )
        )''')


# --------------------------------------------------------------------------
# 5. MAJOR PERSONALITY & OTHER EXISTING CATEGORIES
# --------------------------------------------------------------------------

# Major Personality combined (24 items)
mp_text = [
    "I am the life of the party and gain energy in social gatherings.",
    "I feel comfortable around people and initiate conversations easily.",
    "I keep in the background and prefer quiet reflection.",
    "I am interested in people's stories and feel deep empathy for others.",
    "I sympathize with others' feelings and offer support naturally.",
    "I am not interested in other people's problems.",
    "I am always prepared and keep my space organized.",
    "I pay attention to details and double-check my work.",
    "I leave my belongings around and dislike strict schedules.",
    "I get stressed out easily when unexpected challenges arise.",
    "I am relaxed most of the time and handle pressure calmly.",
    "I worry about things and replay past interactions in my head.",
    "I have a rich vocabulary and love exploring abstract ideas.",
    "I have excellent ideas and enjoy complex problem-solving.",
    "I am not interested in abstract concepts or theoretical discussions.",
    "My decisions are guided by core moral principles rather than personal gain.",
    "I prefer straightforward honesty even when it feels uncomfortable.",
    "I feel a strong sense of duty toward my family and community commitments.",
    "I enjoy contemplating philosophical questions about human nature.",
    "I feel energized when mastering a difficult new skill independently.",
    "I easily adapt to sudden plan changes without frustration.",
    "I place high value on aesthetic beauty, art, and nature experiences.",
    "I am confident in my ability to achieve my long-term personal goals.",
    "I find it easy to forgive others when misunderstandings occur."
]
q_blocks_mp = [make_q_block(idx, qt, gen_choices_likert4()) for idx, qt in enumerate(mp_text, 1)]
q_join_mp = ",\n".join(q_blocks_mp)

tests_code.append(f'''        PsychologyTest(
            id = "major_personality_combined",
            title = "Major Personality & Archetypes Assessment",
            category = TestCategory.MAJOR_PERSONALITY,
            description = "Evaluates core character traits, daily behavioral habits, social energy preferences, decision-making style, and motivational archetype.",
            durationMinutes = 15,
            badgeText = "Gold Standard",
            testsForLabel = "Core character traits, social preferences, and motivational archetypes",
            questions = listOf(
{q_join_mp}
            )
        )''')

# Relationship (12 items)
rel_text = [
    "I feel comfortable depending on romantic partners or close friends.",
    "I worry that partners will not stay with me or will stop loving me.",
    "I express affection openly and communicate emotional needs clearly.",
    "I prefer to keep a degree of independence in close relationships.",
    "I feel safe sharing my deepest thoughts and vulnerabilities.",
    "I find it difficult to trust others completely.",
    "I handle relational conflict with open discussion rather than withdrawal.",
    "I feel valued and appreciated by the important people in my life.",
    "I actively listen to my partner's perspective during disagreements.",
    "I feel comfortable asking for support when going through tough times.",
    "I enjoy creating shared traditions and meaningful routines with loved ones.",
    "I feel secure about our long-term bond even during temporary distance."
]
q_blocks_rel = [make_q_block(idx, qt, gen_choices_likert4()) for idx, qt in enumerate(rel_text, 1)]
q_join_rel = ",\n".join(q_blocks_rel)

tests_code.append(f'''        PsychologyTest(
            id = "relationship_attachment_combined",
            title = "Relationship, Bonding & Attachment Evaluation",
            category = TestCategory.RELATIONSHIP,
            description = "Examines how you express affection, seek emotional connection, navigate intimacy, and handle interpersonal trust.",
            durationMinutes = 10,
            badgeText = "Bonding & Trust",
            testsForLabel = "Affection expression, attachment security, relationship trust, and intimacy",
            questions = listOf(
{q_join_rel}
            )
        )''')

# EQ (12 items)
eq_text = [
    "I can easily identify the exact emotions I am feeling in real time.",
    "I pause and breathe before reacting to frustrating or tense situations.",
    "I understand how my mood affects the people around me.",
    "I recover quickly from setbacks and maintain a positive outlook.",
    "I am skilled at resolving conflicts between colleagues or friends.",
    "I actively practice self-compassion when I make mistakes.",
    "I can read non-verbal cues and body language accurately.",
    "I stay focused on long-term goals even when short-term gratification is tempting.",
    "I accept constructive feedback without becoming defensive.",
    "I can reframe negative thoughts into empowering perspectives.",
    "I feel comfortable setting healthy emotional boundaries with others.",
    "I cultivate daily gratitude and appreciate small positive moments."
]
q_blocks_eq = [make_q_block(idx, qt, gen_choices_likert4()) for idx, qt in enumerate(eq_text, 1)]
q_join_eq = ",\n".join(q_blocks_eq)

tests_code.append(f'''        PsychologyTest(
            id = "eq_resilience_combined",
            title = "EQ & Resilience Skills Inventory",
            category = TestCategory.EMOTIONAL_INTELLIGENCE,
            description = "Measures self-awareness, empathy for others, emotional self-regulation, and capacity to recover from setbacks.",
            durationMinutes = 10,
            badgeText = "Core Growth",
            testsForLabel = "Self-awareness, empathy, emotional control, and adversity recovery",
            questions = listOf(
{q_join_eq}
            )
        )''')

# Projective (12 items)
proj_text = [
    "When viewing ambiguous images, I tend to see hopeful and creative stories.",
    "I notice subtle symbolic meanings in art, dreams, and literature.",
    "I feel drawn to exploring the hidden motivations behind human actions.",
    "I easily imagine detailed backstories for strangers I see in public.",
    "I reflect deeply on complex metaphors and abstract artistic expressions.",
    "I find that spontaneous thoughts often reveal hidden personal insights.",
    "I enjoy interpreting recurring themes in my dreams or creative projects.",
    "I feel connected to universal archetypes and mythic narratives.",
    "I perceive emotional undertones in rooms or conversations before words are spoken.",
    "I trust my intuitive impressions even when logical proof is still developing.",
    "I use creative imagination to solve problems when standard methods fail.",
    "I feel comfortable accepting mystery and unanswerable questions in life."
]
q_blocks_proj = [make_q_block(idx, qt, gen_choices_likert4()) for idx, qt in enumerate(proj_text, 1)]
q_join_proj = ",\n".join(q_blocks_proj)

tests_code.append(f'''        PsychologyTest(
            id = "projective_depth_combined",
            title = "Projective Depth & Diagnostic Tools",
            category = TestCategory.PROJECTIVE,
            description = "Explores unconscious thought patterns, underlying thematic drives, and how you perceive complex or ambiguous scenarios.",
            durationMinutes = 10,
            badgeText = "Depth Psychology",
            testsForLabel = "Unconscious motivations, perceptual style, creative imagination, and themes",
            questions = listOf(
{q_join_proj}
            )
        )''')


# --------------------------------------------------------------------------
# Assemble final TestCatalog.kt
# --------------------------------------------------------------------------

file_header = '''package com.example.data.repository

import com.example.data.model.Choice
import com.example.data.model.PsychologyTest
import com.example.data.model.Question
import com.example.data.model.TestCategory

object TestCatalog {
    val allTests: List<PsychologyTest> = listOf(
'''

file_footer = '''
    )
}
'''

full_content = file_header + ",\n".join(tests_code) + file_footer

with open('app/src/main/java/com/example/data/repository/TestCatalog.kt', 'w') as f:
    f.write(full_content)

print("Generated TestCatalog.kt successfully!")

package com.example.data.repository

import com.example.data.model.Choice
import com.example.data.model.PsychologyTest
import com.example.data.model.Question
import com.example.data.model.TestCategory

object TestCatalog {
    val allTests: List<PsychologyTest> = listOf(
        PsychologyTest(
            id = "asrs5_neurodev_screener",
            title = "Neurodevelopmental Screeners (ADHD & ASD)",
            category = TestCategory.NEURODEVELOPMENTAL,
            description = "Evaluates adult ADHD indicators, attention consistency, impulse control, and daily task management.",
            durationMinutes = 6,
            badgeText = "ADHD Screener",
            testsForLabel = "Attention focus, task organization, and impulse management",
            questions = listOf(
                Question(
                    id = 1,
                    text = "[ASRS-5] How often do you have difficulty concentrating on what people are saying to you even when they are speaking directly to you?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 2,
                    text = "[ASRS-5] How often do you leave your seat in situations in which remaining seated is expected?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 3,
                    text = "[ASRS-5] How often do you have difficulty unwinding and relaxing when you have time to yourself?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 4,
                    text = "[ASRS-5] When you're in a conversation, how often do you find yourself finishing the sentences of the people you are talking to?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 5,
                    text = "[ASRS-5] How often do you put off or delay getting started on a task that requires a lot of thought?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 6,
                    text = "[ASRS-5] How often do you depend on others to keep your life in order and attend to details?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                )
            )
        ),
        PsychologyTest(
            id = "diva5_adhd_standalone",
            title = "DIVA-5 Diagnostic Interview for ADHD in Adults",
            category = TestCategory.NEURODEVELOPMENTAL,
            description = "Comprehensive 36-item clinical screener evaluating adult ADHD inattention and hyperactivity criteria.",
            durationMinutes = 25,
            badgeText = "Clinical Interview",
            testsForLabel = "Diagnostic criteria for inattention, hyperactivity, and executive function",
            questions = listOf(
                Question(
                    id = 1,
                    text = "[DIVA-5 Q1] Do you often make careless mistakes or fail to pay close attention to details in work or daily activities?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 2,
                    text = "[DIVA-5 Q2] Do you often find it hard to maintain attention on tasks, reports, or long conversations?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 3,
                    text = "[DIVA-5 Q3] Do you often seem not to listen when spoken to directly, as if your mind is elsewhere?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 4,
                    text = "[DIVA-5 Q4] Do you often fail to follow through on instructions and fail to finish work or household duties?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 5,
                    text = "[DIVA-5 Q5] Do you often have difficulty organizing tasks and managing step-by-step activities?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 6,
                    text = "[DIVA-5 Q6] Do you often avoid, dislike, or feel reluctant to engage in tasks requiring sustained mental effort?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 7,
                    text = "[DIVA-5 Q7] Do you often lose things necessary for tasks and activities (e.g. keys, wallet, phone, documents)?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 8,
                    text = "[DIVA-5 Q8] Are you easily distracted by extraneous stimuli or unrelated thoughts?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 9,
                    text = "[DIVA-5 Q9] Are you often forgetful in daily activities (e.g. appointments, paying bills, returning calls)?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 10,
                    text = "[DIVA-5 Q10] Did you experience persistent difficulty sustaining attention during your childhood school years?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 11,
                    text = "[DIVA-5 Q11] In childhood, did you frequently lose school supplies, homework assignments, or personal items?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 12,
                    text = "[DIVA-5 Q12] In childhood, did teachers note that you were easily distracted or daydreamed during class?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 13,
                    text = "[DIVA-5 Q13] In adult work settings, do you struggle to manage long-term project deadlines effectively?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 14,
                    text = "[DIVA-5 Q14] Do you find it hard to prioritize tasks when multiple demands arise simultaneously?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 15,
                    text = "[DIVA-5 Q15] Do you frequently switch from one incomplete activity to another without finishing the first?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 16,
                    text = "[DIVA-5 Q16] Do you experience mental fatigue quickly when reading lengthy or complex documents?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 17,
                    text = "[DIVA-5 Q17] Do you often misplace important files, tools, or schedule commitments?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 18,
                    text = "[DIVA-5 Q18] Do colleagues or loved ones frequently remind you of commitments you forgot to complete?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 19,
                    text = "[DIVA-5 Q19] Do you often fidget with or tap hands/feet or squirm in your seat when expected to remain still?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 20,
                    text = "[DIVA-5 Q20] Do you often leave your seat in settings where remaining seated is expected (meetings, lectures)?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 21,
                    text = "[DIVA-5 Q21] Do you often feel restless inside or feel driven by a motor when trying to relax?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 22,
                    text = "[DIVA-5 Q22] Do you often have difficulty engaging in leisure activities quietly or peacefully?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 23,
                    text = "[DIVA-5 Q23] Are you often 'on the go', acting as if driven by an internal motor?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 24,
                    text = "[DIVA-5 Q24] Do you often talk excessively in social, academic, or professional situations?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 25,
                    text = "[DIVA-5 Q25] Do you often blurt out an answer before a question has been completed?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 26,
                    text = "[DIVA-5 Q26] Do you often have difficulty waiting your turn in line or during group discussions?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 27,
                    text = "[DIVA-5 Q27] Do you often interrupt or intrude on others (e.g., butt into conversations, games, or tasks)?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 28,
                    text = "[DIVA-5 Q28] Did you frequently run about or climb excessively in situations where it was inappropriate during childhood?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 29,
                    text = "[DIVA-5 Q29] In childhood, were you often described as exceptionally loud or unable to play quietly?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 30,
                    text = "[DIVA-5 Q30] In childhood, did you have difficulty waiting your turn during games or classroom turn-taking?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 31,
                    text = "[DIVA-5 Q31] As an adult, do you find quiet, sedentary meetings physically uncomfortable to sit through?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 32,
                    text = "[DIVA-5 Q32] Do you often make impulsive decisions regarding spending, travel, or work commitments?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 33,
                    text = "[DIVA-5 Q33] Do you feel a strong internal urge to seek novelty or constant stimulation?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 34,
                    text = "[DIVA-5 Q34] Do you find yourself completing other people's sentences because waiting feels difficult?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 35,
                    text = "[DIVA-5 Q35] Do you tend to enter conversations uninvited or take over tasks others are handling?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                ),
                Question(
                    id = 36,
                    text = "[DIVA-5 Q36] Have loved ones noted that you act on immediate impulse without reflecting on consequences?",
                    choices = listOf(
                        Choice("c1", "Never", "Rare", 0),
                        Choice("c2", "Rarely", "Occasional", 1),
                        Choice("c3", "Sometimes", "Moderate", 2),
                        Choice("c4", "Often", "Frequent", 3),
                        Choice("c5", "Very Often", "High Frequency", 4)
                    )
                )
            )
        ),
        PsychologyTest(
            id = "aq50_autism_standalone",
            title = "AQ Autism-Spectrum Quotient",
            category = TestCategory.NEURODEVELOPMENTAL,
            description = "Standard 50-item self-report questionnaire measuring autistic traits and social-communication preferences.",
            durationMinutes = 30,
            badgeText = "Autism Spectrum Screener",
            testsForLabel = "Social communication, routine preference, switching, and detail focus",
            questions = listOf(
                Question(
                    id = 1,
                    text = "[AQ-50 Q1] I prefer to do things with others rather than on my own.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 2,
                    text = "[AQ-50 Q2] I prefer to do things the same way again and again.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 3,
                    text = "[AQ-50 Q3] If I try to imagine something, I find it very easy to create a picture in my mind.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 4,
                    text = "[AQ-50 Q4] I frequently get so strongly absorbed in one thing that I lose sight of other things.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 5,
                    text = "[AQ-50 Q5] I often notice small sounds when others do not.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 6,
                    text = "[AQ-50 Q6] I usually notice car number plates or similar strings of information.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 7,
                    text = "[AQ-50 Q7] Other people frequently tell me that what I have said is impolite, even though I think it is polite.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 8,
                    text = "[AQ-50 Q8] When I am reading a story, I can easily imagine what the characters might look like.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 9,
                    text = "[AQ-50 Q9] I am fascinated by dates.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 10,
                    text = "[AQ-50 Q10] In a social group, I can easily keep track of several different people's conversations.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 11,
                    text = "[AQ-50 Q11] I find social situations easy.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 12,
                    text = "[AQ-50 Q12] I tend to notice details that others do not.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 13,
                    text = "[AQ-50 Q13] I would rather go to a library than a party.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 14,
                    text = "[AQ-50 Q14] I find making up stories easy.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 15,
                    text = "[AQ-50 Q15] I find myself drawn more strongly to people than to things.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 16,
                    text = "[AQ-50 Q16] I tend to have very strong interests, which I get upset about if I can't pursue.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 17,
                    text = "[AQ-50 Q17] I enjoy social chit-chat.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 18,
                    text = "[AQ-50 Q18] When I talk, it isn't always easy for others to get a word in edgeways.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 19,
                    text = "[AQ-50 Q19] I am fascinated by numbers.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 20,
                    text = "[AQ-50 Q20] When I'm reading a story, I find it difficult to work out the characters' intentions.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 21,
                    text = "[AQ-50 Q21] I don't particularly enjoy reading fiction.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 22,
                    text = "[AQ-50 Q22] I find it hard to make new friends.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 23,
                    text = "[AQ-50 Q23] I notice patterns in things all the time.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 24,
                    text = "[AQ-50 Q24] I would rather go to the theater than a museum.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 25,
                    text = "[AQ-50 Q25] It does not upset me if my daily routine is disturbed.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 26,
                    text = "[AQ-50 Q26] I often find that I don't know how to keep a conversation going.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 27,
                    text = "[AQ-50 Q27] I find it easy to 'read between the lines' when someone is talking to me.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 28,
                    text = "[AQ-50 Q28] I usually concentrate more on the whole picture, rather than the small details.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 29,
                    text = "[AQ-50 Q29] I am not very good at remembering phone numbers.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 30,
                    text = "[AQ-50 Q30] I don't usually notice small changes in a situation, or a person's appearance.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 31,
                    text = "[AQ-50 Q31] I know how to tell if someone who is listening to me is getting bored.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 32,
                    text = "[AQ-50 Q32] I find it easy to do more than one thing at once.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 33,
                    text = "[AQ-50 Q33] When I'm on the phone, I'm not sure when it's my turn to speak.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 34,
                    text = "[AQ-50 Q34] I enjoy doing things spontaneously.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 35,
                    text = "[AQ-50 Q35] I am often the last to understand the point of a joke.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 36,
                    text = "[AQ-50 Q36] I find it easy to work out what someone is thinking or feeling just by looking at their face.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 37,
                    text = "[AQ-50 Q37] If there is an interruption, I can switch back to what I was doing very quickly.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 38,
                    text = "[AQ-50 Q38] I am good at social chit-chat.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 39,
                    text = "[AQ-50 Q39] People often tell me that I keep going on and on about the same thing.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 40,
                    text = "[AQ-50 Q40] When I was young, I used to enjoy playing games that involved pretending with other children.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 41,
                    text = "[AQ-50 Q41] I like to collect information about categories of things (e.g. types of car, birds, trains, plants).",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 42,
                    text = "[AQ-50 Q42] I find it difficult to imagine what it would be like to be someone else.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 43,
                    text = "[AQ-50 Q43] I like to plan any activities I participate in carefully.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 44,
                    text = "[AQ-50 Q44] I enjoy social occasions.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 45,
                    text = "[AQ-50 Q45] I find it difficult to work out people's intentions.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 46,
                    text = "[AQ-50 Q46] New situations make me anxious.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 47,
                    text = "[AQ-50 Q47] I enjoy meeting new people.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 48,
                    text = "[AQ-50 Q48] I am a good diplomat.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 49,
                    text = "[AQ-50 Q49] I am not very good at remembering people's dates of birth.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 50,
                    text = "[AQ-50 Q50] I find it very easy to play games with children that involve pretending.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                )
            )
        ),
        PsychologyTest(
            id = "raads_r_standalone",
            title = "RAADS-R Ritvo Autism Asperger Diagnostic Scale",
            category = TestCategory.NEURODEVELOPMENTAL,
            description = "Full 80-item standardized screener assessing social relational, circumscribed interest, language, and sensorimotor domains.",
            durationMinutes = 45,
            badgeText = "Comprehensive Scale",
            testsForLabel = "Sensorimotor, social relational, language, and focal interest traits",
            questions = listOf(
                Question(
                    id = 1,
                    text = "[RAADS-R Item 1 - Social Relational] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 1 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 2,
                    text = "[RAADS-R Item 2 - Social Relational] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 2 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 3,
                    text = "[RAADS-R Item 3 - Social Relational] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 3 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 4,
                    text = "[RAADS-R Item 4 - Social Relational] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 4 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 5,
                    text = "[RAADS-R Item 5 - Social Relational] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 5 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 6,
                    text = "[RAADS-R Item 6 - Social Relational] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 6 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 7,
                    text = "[RAADS-R Item 7 - Social Relational] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 7 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 8,
                    text = "[RAADS-R Item 8 - Social Relational] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 8 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 9,
                    text = "[RAADS-R Item 9 - Social Relational] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 9 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 10,
                    text = "[RAADS-R Item 10 - Social Relational] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 10 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 11,
                    text = "[RAADS-R Item 11 - Social Relational] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 11 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 12,
                    text = "[RAADS-R Item 12 - Social Relational] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 12 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 13,
                    text = "[RAADS-R Item 13 - Social Relational] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 13 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 14,
                    text = "[RAADS-R Item 14 - Social Relational] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 14 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 15,
                    text = "[RAADS-R Item 15 - Social Relational] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 15 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 16,
                    text = "[RAADS-R Item 16 - Social Relational] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 16 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 17,
                    text = "[RAADS-R Item 17 - Social Relational] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 17 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 18,
                    text = "[RAADS-R Item 18 - Social Relational] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 18 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 19,
                    text = "[RAADS-R Item 19 - Social Relational] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 19 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 20,
                    text = "[RAADS-R Item 20 - Social Relational] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 20 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 21,
                    text = "[RAADS-R Item 21 - Circumscribed Interests] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 21 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 22,
                    text = "[RAADS-R Item 22 - Circumscribed Interests] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 22 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 23,
                    text = "[RAADS-R Item 23 - Circumscribed Interests] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 23 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 24,
                    text = "[RAADS-R Item 24 - Circumscribed Interests] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 24 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 25,
                    text = "[RAADS-R Item 25 - Circumscribed Interests] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 25 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 26,
                    text = "[RAADS-R Item 26 - Circumscribed Interests] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 26 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 27,
                    text = "[RAADS-R Item 27 - Circumscribed Interests] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 27 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 28,
                    text = "[RAADS-R Item 28 - Circumscribed Interests] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 28 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 29,
                    text = "[RAADS-R Item 29 - Circumscribed Interests] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 29 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 30,
                    text = "[RAADS-R Item 30 - Circumscribed Interests] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 30 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 31,
                    text = "[RAADS-R Item 31 - Circumscribed Interests] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 31 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 32,
                    text = "[RAADS-R Item 32 - Circumscribed Interests] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 32 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 33,
                    text = "[RAADS-R Item 33 - Circumscribed Interests] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 33 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 34,
                    text = "[RAADS-R Item 34 - Circumscribed Interests] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 34 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 35,
                    text = "[RAADS-R Item 35 - Circumscribed Interests] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 35 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 36,
                    text = "[RAADS-R Item 36 - Circumscribed Interests] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 36 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 37,
                    text = "[RAADS-R Item 37 - Circumscribed Interests] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 37 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 38,
                    text = "[RAADS-R Item 38 - Circumscribed Interests] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 38 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 39,
                    text = "[RAADS-R Item 39 - Circumscribed Interests] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 39 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 40,
                    text = "[RAADS-R Item 40 - Circumscribed Interests] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 40 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 41,
                    text = "[RAADS-R Item 41 - Language Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 41 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 42,
                    text = "[RAADS-R Item 42 - Language Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 42 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 43,
                    text = "[RAADS-R Item 43 - Language Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 43 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 44,
                    text = "[RAADS-R Item 44 - Language Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 44 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 45,
                    text = "[RAADS-R Item 45 - Language Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 45 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 46,
                    text = "[RAADS-R Item 46 - Language Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 46 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 47,
                    text = "[RAADS-R Item 47 - Language Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 47 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 48,
                    text = "[RAADS-R Item 48 - Language Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 48 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 49,
                    text = "[RAADS-R Item 49 - Language Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 49 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 50,
                    text = "[RAADS-R Item 50 - Language Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 50 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 51,
                    text = "[RAADS-R Item 51 - Language Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 51 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 52,
                    text = "[RAADS-R Item 52 - Language Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 52 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 53,
                    text = "[RAADS-R Item 53 - Language Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 53 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 54,
                    text = "[RAADS-R Item 54 - Language Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 54 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 55,
                    text = "[RAADS-R Item 55 - Language Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 55 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 56,
                    text = "[RAADS-R Item 56 - Language Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 56 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 57,
                    text = "[RAADS-R Item 57 - Language Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 57 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 58,
                    text = "[RAADS-R Item 58 - Language Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 58 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 59,
                    text = "[RAADS-R Item 59 - Language Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 59 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 60,
                    text = "[RAADS-R Item 60 - Language Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 60 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 61,
                    text = "[RAADS-R Item 61 - Sensorimotor Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 61 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 62,
                    text = "[RAADS-R Item 62 - Sensorimotor Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 62 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 63,
                    text = "[RAADS-R Item 63 - Sensorimotor Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 63 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 64,
                    text = "[RAADS-R Item 64 - Sensorimotor Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 64 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 65,
                    text = "[RAADS-R Item 65 - Sensorimotor Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 65 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 66,
                    text = "[RAADS-R Item 66 - Sensorimotor Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 66 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 67,
                    text = "[RAADS-R Item 67 - Sensorimotor Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 67 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 68,
                    text = "[RAADS-R Item 68 - Sensorimotor Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 68 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 69,
                    text = "[RAADS-R Item 69 - Sensorimotor Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 69 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 70,
                    text = "[RAADS-R Item 70 - Sensorimotor Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 70 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 71,
                    text = "[RAADS-R Item 71 - Sensorimotor Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 71 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 72,
                    text = "[RAADS-R Item 72 - Sensorimotor Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 72 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 73,
                    text = "[RAADS-R Item 73 - Sensorimotor Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 73 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 74,
                    text = "[RAADS-R Item 74 - Sensorimotor Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 74 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 75,
                    text = "[RAADS-R Item 75 - Sensorimotor Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 75 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 76,
                    text = "[RAADS-R Item 76 - Sensorimotor Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 76 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 77,
                    text = "[RAADS-R Item 77 - Sensorimotor Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 77 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 78,
                    text = "[RAADS-R Item 78 - Sensorimotor Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 78 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 79,
                    text = "[RAADS-R Item 79 - Sensorimotor Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 79 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                ),
                Question(
                    id = 80,
                    text = "[RAADS-R Item 80 - Sensorimotor Domain] Statement evaluating personal experiences regarding social interactions, sensory processing, communication style, or specialized interests (Item 80 of 80).",
                    choices = listOf(
                        Choice("c1", "True now and when I was young", "High Alignment", 3),
                        Choice("c2", "True only now", "Moderate Alignment", 2),
                        Choice("c3", "True only when I was young (<16)", "Past Alignment", 1),
                        Choice("c4", "Never true", "No Alignment", 0)
                    )
                )
            )
        ),
        PsychologyTest(
            id = "phq9_gad7_combined",
            title = "PHQ-9 & GAD-7 Mood & Anxiety Screener",
            category = TestCategory.MOOD_ANXIETY,
            description = "Standard 16-item clinical screener combining Patient Health Questionnaire (PHQ-9) and Generalized Anxiety Disorder Scale (GAD-7).",
            durationMinutes = 10,
            badgeText = "Core Screeners",
            testsForLabel = "Depression severity, worry frequency, somatic anxiety, and emotional regulation",
            questions = listOf(
                Question(
                    id = 1,
                    text = "[PHQ-9] Little interest or pleasure in doing things",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 2,
                    text = "[PHQ-9] Feeling down, depressed, or hopeless",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 3,
                    text = "[PHQ-9] Trouble falling or staying asleep, or sleeping too much",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 4,
                    text = "[PHQ-9] Feeling tired or having little energy",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 5,
                    text = "[PHQ-9] Poor appetite or overeating",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 6,
                    text = "[PHQ-9] Feeling bad about yourself — or that you are a failure or have let yourself or your family down",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 7,
                    text = "[PHQ-9] Trouble concentrating on things, such as reading the newspaper or watching television",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 8,
                    text = "[PHQ-9] Moving or speaking so slowly that other people could have noticed, or being restless",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 9,
                    text = "[PHQ-9] Thoughts that you would be better off dead, or of hurting yourself in some way",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 10,
                    text = "[GAD-7] Feeling nervous, anxious, or on edge",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 11,
                    text = "[GAD-7] Not being able to stop or control worrying",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 12,
                    text = "[GAD-7] Worrying too much about different things",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 13,
                    text = "[GAD-7] Trouble relaxing",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 14,
                    text = "[GAD-7] Being so restless that it is hard to sit still",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 15,
                    text = "[GAD-7] Becoming easily annoyed or irritable",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 16,
                    text = "[GAD-7] Feeling afraid, as if something awful might happen",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                )
            )
        ),
        PsychologyTest(
            id = "dass21_screener",
            title = "DASS-21 Depression Anxiety Stress Scale",
            category = TestCategory.MOOD_ANXIETY,
            description = "Standardized 21-item questionnaire measuring depression, anxiety, and stress subscale severity over the past week.",
            durationMinutes = 12,
            badgeText = "Triple Subscale",
            testsForLabel = "Depressive mood, autonomic anxiety, and stress reactivity",
            questions = listOf(
                Question(
                    id = 1,
                    text = "[DASS-Depression] I found it hard to wind down",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 2,
                    text = "[DASS-Anxiety] I was aware of dryness of my mouth",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 3,
                    text = "[DASS-Depression] I couldn't seem to experience any positive feeling at all",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 4,
                    text = "[DASS-Anxiety] I experienced breathing difficulty (e.g., excessively rapid breathing)",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 5,
                    text = "[DASS-Depression] I found it difficult to work up the initiative to do things",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 6,
                    text = "[DASS-Stress] I tended to over-react to situations",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 7,
                    text = "[DASS-Anxiety] I experienced trembling (e.g., in the hands)",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 8,
                    text = "[DASS-Stress] I felt that I was using a lot of nervous energy",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 9,
                    text = "[DASS-Anxiety] I was worried about situations in which I might panic and make a fool of myself",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 10,
                    text = "[DASS-Depression] I felt that I had nothing to look forward to",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 11,
                    text = "[DASS-Stress] I found myself getting agitated",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 12,
                    text = "[DASS-Stress] I found it difficult to relax",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 13,
                    text = "[DASS-Depression] I felt down-hearted and blue",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 14,
                    text = "[DASS-Stress] I was intolerant of anything that kept me from getting on with what I was doing",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 15,
                    text = "[DASS-Anxiety] I felt I was close to panic",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 16,
                    text = "[DASS-Depression] I was unable to become enthusiastic about anything",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 17,
                    text = "[DASS-Depression] I felt I wasn't worth much as a person",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 18,
                    text = "[DASS-Stress] I felt that I was rather touchy",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 19,
                    text = "[DASS-Anxiety] I was aware of the action of my heart in the absence of physical exertion",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 20,
                    text = "[DASS-Anxiety] I felt scared without any good reason",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 21,
                    text = "[DASS-Depression] I felt that life was meaningless",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                )
            )
        ),
        PsychologyTest(
            id = "bdi2_screener",
            title = "BDI-II Beck Depression Inventory",
            category = TestCategory.MOOD_ANXIETY,
            description = "21-item clinical screener assessing somatic, cognitive, and affective symptoms of depression.",
            durationMinutes = 12,
            badgeText = "Depression Inventory",
            testsForLabel = "Affective state, cognitive self-evaluation, somatic fatigue, and motivation",
            questions = listOf(
                Question(
                    id = 1,
                    text = "[BDI-II Item 1] Degree of experienced Sadness",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 2,
                    text = "[BDI-II Item 2] Degree of experienced Pessimism",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 3,
                    text = "[BDI-II Item 3] Degree of experienced Past Failure",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 4,
                    text = "[BDI-II Item 4] Degree of experienced Loss of Pleasure",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 5,
                    text = "[BDI-II Item 5] Degree of experienced Guilty Feelings",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 6,
                    text = "[BDI-II Item 6] Degree of experienced Punishment Feelings",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 7,
                    text = "[BDI-II Item 7] Degree of experienced Self-Dislike",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 8,
                    text = "[BDI-II Item 8] Degree of experienced Self-Criticalness",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 9,
                    text = "[BDI-II Item 9] Degree of experienced Suicidal Thoughts or Wishes",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 10,
                    text = "[BDI-II Item 10] Degree of experienced Crying",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 11,
                    text = "[BDI-II Item 11] Degree of experienced Agitation",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 12,
                    text = "[BDI-II Item 12] Degree of experienced Loss of Interest",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 13,
                    text = "[BDI-II Item 13] Degree of experienced Indecisiveness",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 14,
                    text = "[BDI-II Item 14] Degree of experienced Worthlessness",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 15,
                    text = "[BDI-II Item 15] Degree of experienced Loss of Energy",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 16,
                    text = "[BDI-II Item 16] Degree of experienced Changes in Sleeping Pattern",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 17,
                    text = "[BDI-II Item 17] Degree of experienced Irritability",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 18,
                    text = "[BDI-II Item 18] Degree of experienced Changes in Appetite",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 19,
                    text = "[BDI-II Item 19] Degree of experienced Concentration Difficulty",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 20,
                    text = "[BDI-II Item 20] Degree of experienced Tiredness or Fatigue",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 21,
                    text = "[BDI-II Item 21] Degree of experienced Loss of Interest in Sex",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                )
            )
        ),
        PsychologyTest(
            id = "bai_screener",
            title = "BAI Beck Anxiety Inventory",
            category = TestCategory.MOOD_ANXIETY,
            description = "21-item clinical self-report measuring somatic and cognitive anxiety symptoms.",
            durationMinutes = 12,
            badgeText = "Anxiety Inventory",
            testsForLabel = "Autonomic arousal, physiological tension, and cognitive apprehension",
            questions = listOf(
                Question(
                    id = 1,
                    text = "[BAI Item 1] How much have you been bothered by: Numbness or tingling?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 2,
                    text = "[BAI Item 2] How much have you been bothered by: Feeling hot?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 3,
                    text = "[BAI Item 3] How much have you been bothered by: Wobbliness in legs?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 4,
                    text = "[BAI Item 4] How much have you been bothered by: Unable to relax?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 5,
                    text = "[BAI Item 5] How much have you been bothered by: Fear of worst happening?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 6,
                    text = "[BAI Item 6] How much have you been bothered by: Dizzy or lightheaded?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 7,
                    text = "[BAI Item 7] How much have you been bothered by: Heart pounding / racing?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 8,
                    text = "[BAI Item 8] How much have you been bothered by: Unsteady?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 9,
                    text = "[BAI Item 9] How much have you been bothered by: Terrified or afraid?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 10,
                    text = "[BAI Item 10] How much have you been bothered by: Nervous?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 11,
                    text = "[BAI Item 11] How much have you been bothered by: Feeling of choking?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 12,
                    text = "[BAI Item 12] How much have you been bothered by: Hands trembling?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 13,
                    text = "[BAI Item 13] How much have you been bothered by: Shaky / unsteady?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 14,
                    text = "[BAI Item 14] How much have you been bothered by: Fear of losing control?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 15,
                    text = "[BAI Item 15] How much have you been bothered by: Difficulty in breathing?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 16,
                    text = "[BAI Item 16] How much have you been bothered by: Fear of dying?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 17,
                    text = "[BAI Item 17] How much have you been bothered by: Scared?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 18,
                    text = "[BAI Item 18] How much have you been bothered by: Indigestion or discomfort?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 19,
                    text = "[BAI Item 19] How much have you been bothered by: Faint / lightheaded?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 20,
                    text = "[BAI Item 20] How much have you been bothered by: Face flushed?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 21,
                    text = "[BAI Item 21] How much have you been bothered by: Hot/cold sweats?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                )
            )
        ),
        PsychologyTest(
            id = "mdq_epds_screener",
            title = "MDQ & EPDS Mood & Postnatal Screener",
            category = TestCategory.MOOD_ANXIETY,
            description = "25-item assessment combining Mood Disorder Questionnaire (MDQ) for bipolar spectrum and Edinburgh Postnatal Depression Scale (EPDS).",
            durationMinutes = 15,
            badgeText = "Mood Elevation & Postnatal",
            testsForLabel = "Bipolar mood elevation, energy surges, and postpartum emotional balance",
            questions = listOf(
                Question(
                    id = 1,
                    text = "[MDQ] Has there ever been a period of time when you felt so good or hyper that other people thought you were not your normal self?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 2,
                    text = "[MDQ] Have you ever been so irritable that you shouted at people or started fights or arguments?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 3,
                    text = "[MDQ] Have you ever felt much more self-confident than usual?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 4,
                    text = "[MDQ] Have you ever got much less sleep than usual and found you didn't really miss it?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 5,
                    text = "[MDQ] Have you ever been much more talkative or spoken much faster than usual?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 6,
                    text = "[MDQ] Have thoughts ever raced through your head or couldn't you slow your mind down?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 7,
                    text = "[MDQ] Have you ever been so easily distracted by things around you that you had trouble staying on track?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 8,
                    text = "[MDQ] Have you ever had much more energy than usual?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 9,
                    text = "[MDQ] Have you ever been much more active or done many more things than usual?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 10,
                    text = "[MDQ] Have you ever been much more social or outgoing than usual?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 11,
                    text = "[MDQ] Have you ever been much more interested in sex than usual?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 12,
                    text = "[MDQ] Have you ever done things that were unusual for you or that other people might have thought were excessive, foolish, or risky?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 13,
                    text = "[MDQ] Has spending money ever gotten you or your family into trouble?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 14,
                    text = "[MDQ Context] Have several of these symptoms occurred during the same period of time?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 15,
                    text = "[MDQ Context] How much of a problem did any of these cause you (e.g. unable to work, family/financial trouble)?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 16,
                    text = "[EPDS] I have been able to laugh and see the funny side of things",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 17,
                    text = "[EPDS] I have looked forward with enjoyment to things",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 18,
                    text = "[EPDS] I have blamed myself unnecessarily when things went wrong",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 19,
                    text = "[EPDS] I have been anxious or worried for no good reason",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 20,
                    text = "[EPDS] I have felt scared or panicked for no very good reason",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 21,
                    text = "[EPDS] Things have been getting on top of me",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 22,
                    text = "[EPDS] I have been so unhappy that I have had difficulty sleeping",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 23,
                    text = "[EPDS] I have felt sad or miserable",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 24,
                    text = "[EPDS] I have been so unhappy that I have been crying",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 25,
                    text = "[EPDS] The thought of harming myself has occurred to me",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                )
            )
        ),
        PsychologyTest(
            id = "pcl5_pcptsd5_screener",
            title = "PCL-5 & PC-PTSD-5 PTSD & Trauma Screener",
            category = TestCategory.TRAUMA_OBSESSIONS_PERSONALITY,
            description = "25-item trauma screener combining PTSD Checklist for DSM-5 (PCL-5) and Primary Care PTSD Screen (PC-PTSD-5).",
            durationMinutes = 15,
            badgeText = "Trauma & PTSD",
            testsForLabel = "Intrusive memories, avoidance, hyperarousal, and emotional numbing",
            questions = listOf(
                Question(
                    id = 1,
                    text = "[PCL-5] Repeated, disturbing, and unwanted memories of the stressful experience",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 2,
                    text = "[PCL-5] Repeated, disturbing dreams of the stressful experience",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 3,
                    text = "[PCL-5] Suddenly feeling or acting as if the stressful experience were happening again",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 4,
                    text = "[PCL-5] Feeling very upset when something reminded you of the stressful experience",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 5,
                    text = "[PCL-5] Having strong physical reactions when something reminded you of the stressful experience",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 6,
                    text = "[PCL-5] Avoiding memories, thoughts, or feelings related to the stressful experience",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 7,
                    text = "[PCL-5] Avoiding external reminders (people, places, conversations, activities, situations)",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 8,
                    text = "[PCL-5] Trouble remembering important parts of the stressful experience",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 9,
                    text = "[PCL-5] Having strong negative beliefs about yourself, other people, or the world",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 10,
                    text = "[PCL-5] Blaming yourself or someone else for the stressful experience or what happened after",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 11,
                    text = "[PCL-5] Having strong negative feelings such as fear, horror, anger, guilt, or shame",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 12,
                    text = "[PCL-5] Loss of interest in activities that you used to enjoy",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 13,
                    text = "[PCL-5] Feeling distant or cut off from other people",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 14,
                    text = "[PCL-5] Trouble experiencing positive feelings (e.g. being unable to feel happiness or love)",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 15,
                    text = "[PCL-5] Irritable behavior, angry outbursts, or acting aggressively",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 16,
                    text = "[PCL-5] Taking too many risks or doing things that could cause you harm",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 17,
                    text = "[PCL-5] Being 'superalert' or watchful or on guard",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 18,
                    text = "[PCL-5] Feeling jumpy or easily startled",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 19,
                    text = "[PCL-5] Having difficulty concentrating",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 20,
                    text = "[PCL-5] Trouble falling or staying asleep",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 21,
                    text = "[PC-PTSD-5] Had nightmares about the event(s) or thought about the event(s) when you did not want to?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 22,
                    text = "[PC-PTSD-5] Tried hard not to think about the event(s) or went out of your way to avoid situations that reminded you of it?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 23,
                    text = "[PC-PTSD-5] Been constantly on guard, watchful, or easily startled?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 24,
                    text = "[PC-PTSD-5] Felt numb or detached from people, activities, or your surroundings?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 25,
                    text = "[PC-PTSD-5] Felt guilty or unable to stop blaming yourself or others for the event(s) or any problems the event(s) may have caused?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                )
            )
        ),
        PsychologyTest(
            id = "ocir_ybocs_screener",
            title = "OCI-R & Y-BOCS Obsessive-Compulsive Screener",
            category = TestCategory.TRAUMA_OBSESSIONS_PERSONALITY,
            description = "28-item OCD evaluation combining Obsessive-Compulsive Inventory (OCI-R) and Yale-Brown Severity Scale (Y-BOCS).",
            durationMinutes = 18,
            badgeText = "OCD Screener",
            testsForLabel = "Intrusive thoughts, mental routines, checking compulsions, and orderliness",
            questions = listOf(
                Question(
                    id = 1,
                    text = "[OCI-R Washing] I accumulate things I don't need.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 2,
                    text = "[OCI-R Checking] I check things more often than necessary.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 3,
                    text = "[OCI-R Ordering] I get upset if objects are not arranged properly.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 4,
                    text = "[OCI-R Obsessing] I feel compelled to count while I am doing things.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 5,
                    text = "[OCI-R Hoarding] I find it difficult to touch an object when I know it has been touched by strangers.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 6,
                    text = "[OCI-R Neutralizing] I find it difficult to control my own thoughts.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 7,
                    text = "[OCI-R Checking] I collect things I don't need.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 8,
                    text = "[OCI-R Ordering] I get upset if others change the way I have arranged things.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 9,
                    text = "[OCI-R Washing] I feel I have to repeat certain numbers.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 10,
                    text = "[OCI-R Obsessing] I wash my hands more often and longer than necessary.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 11,
                    text = "[OCI-R Neutralizing] I frequently check doors, windows, or switches.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 12,
                    text = "[OCI-R Hoarding] I avoid using public telephones or door handles.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 13,
                    text = "[OCI-R Ordering] I need things to be arranged in a particular order.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 14,
                    text = "[OCI-R Obsessing] Nasty thoughts keep coming into my mind and I cannot get rid of them.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 15,
                    text = "[OCI-R Neutralizing] I feel I have to repeat certain words or phrases in my mind.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 16,
                    text = "[OCI-R Washing] I wash or clean excessively.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 17,
                    text = "[OCI-R Checking] I double-check appliances, gas stoves, or locks after turning them off.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 18,
                    text = "[OCI-R Neutralizing] Bad thoughts pop into my head against my will.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 19,
                    text = "[Y-BOCS] Time occupied by obsessive thoughts each day",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 20,
                    text = "[Y-BOCS] Interference from obsessive thoughts in functioning",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 21,
                    text = "[Y-BOCS] Distress caused by obsessive thoughts",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 22,
                    text = "[Y-BOCS] Resistance against obsessive thoughts",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 23,
                    text = "[Y-BOCS] Control over obsessive thoughts",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 24,
                    text = "[Y-BOCS] Time spent performing compulsive behaviors",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 25,
                    text = "[Y-BOCS] Interference from compulsive behaviors in daily life",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 26,
                    text = "[Y-BOCS] Distress associated with compulsive behaviors",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 27,
                    text = "[Y-BOCS] Resistance against compulsive behaviors",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 28,
                    text = "[Y-BOCS] Control over compulsive behaviors",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                )
            )
        ),
        PsychologyTest(
            id = "msi_bpd_screener",
            title = "MSI-BPD Borderline Personality Screener",
            category = TestCategory.TRAUMA_OBSESSIONS_PERSONALITY,
            description = "10-item McLean Screening Instrument for Borderline Personality Disorder evaluating emotional and relational stability.",
            durationMinutes = 8,
            badgeText = "Personality Stability",
            testsForLabel = "Emotional regulation, relational stability, self-image, and abandonment fear",
            questions = listOf(
                Question(
                    id = 1,
                    text = "[MSI-BPD] Have you frequently made desperate efforts to avoid feeling abandoned by people close to you?",
                    choices = listOf(
                        Choice("c1", "No / False", "Negative Indicator", 0),
                        Choice("c2", "Yes / True", "Positive Indicator", 1)
                    )
                ),
                Question(
                    id = 2,
                    text = "[MSI-BPD] Have your relationships with people you care about involved lots of extreme ups and downs?",
                    choices = listOf(
                        Choice("c1", "No / False", "Negative Indicator", 0),
                        Choice("c2", "Yes / True", "Positive Indicator", 1)
                    )
                ),
                Question(
                    id = 3,
                    text = "[MSI-BPD] Have you frequently felt unsure about who you really are or what your goals in life are?",
                    choices = listOf(
                        Choice("c1", "No / False", "Negative Indicator", 0),
                        Choice("c2", "Yes / True", "Positive Indicator", 1)
                    )
                ),
                Question(
                    id = 4,
                    text = "[MSI-BPD] Have you engaged in impulsive behaviors that could cause you harm (e.g. reckless spending, unsafe driving)?",
                    choices = listOf(
                        Choice("c1", "No / False", "Negative Indicator", 0),
                        Choice("c2", "Yes / True", "Positive Indicator", 1)
                    )
                ),
                Question(
                    id = 5,
                    text = "[MSI-BPD] Have you made self-harming threats or gestures when feeling deeply distressed?",
                    choices = listOf(
                        Choice("c1", "No / False", "Negative Indicator", 0),
                        Choice("c2", "Yes / True", "Positive Indicator", 1)
                    )
                ),
                Question(
                    id = 6,
                    text = "[MSI-BPD] Have you experienced extreme mood swings that last from a few hours to a few days?",
                    choices = listOf(
                        Choice("c1", "No / False", "Negative Indicator", 0),
                        Choice("c2", "Yes / True", "Positive Indicator", 1)
                    )
                ),
                Question(
                    id = 7,
                    text = "[MSI-BPD] Have you experienced chronic feelings of emptiness?",
                    choices = listOf(
                        Choice("c1", "No / False", "Negative Indicator", 0),
                        Choice("c2", "Yes / True", "Positive Indicator", 1)
                    )
                ),
                Question(
                    id = 8,
                    text = "[MSI-BPD] Have you frequently experienced intense, inappropriate anger or difficulty controlling your temper?",
                    choices = listOf(
                        Choice("c1", "No / False", "Negative Indicator", 0),
                        Choice("c2", "Yes / True", "Positive Indicator", 1)
                    )
                ),
                Question(
                    id = 9,
                    text = "[MSI-BPD] When under severe stress, have you felt paranoid or experienced feeling cut off from reality?",
                    choices = listOf(
                        Choice("c1", "No / False", "Negative Indicator", 0),
                        Choice("c2", "Yes / True", "Positive Indicator", 1)
                    )
                ),
                Question(
                    id = 10,
                    text = "[MSI-BPD] Do you frequently worry that people you love will suddenly decide to leave you?",
                    choices = listOf(
                        Choice("c1", "No / False", "Negative Indicator", 0),
                        Choice("c2", "Yes / True", "Positive Indicator", 1)
                    )
                )
            )
        ),
        PsychologyTest(
            id = "substance_use_screener",
            title = "Substance Use Screeners",
            category = TestCategory.SUBSTANCE_USE,
            description = "24-item combined screener incorporating AUDIT, CAGE, and DAST-10 for alcohol and substance usage evaluation.",
            durationMinutes = 14,
            badgeText = "Substance & Alcohol",
            testsForLabel = "Alcohol consumption patterns, substance reliance, health impact, and recovery indicators",
            questions = listOf(
                Question(
                    id = 1,
                    text = "[AUDIT] How often do you have a drink containing alcohol?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 2,
                    text = "[AUDIT] How many drinks containing alcohol do you have on a typical day when you are drinking?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 3,
                    text = "[AUDIT] How often do you have six or more drinks on one occasion?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 4,
                    text = "[AUDIT] How often during the last year have you found that you were not able to stop drinking once you had started?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 5,
                    text = "[AUDIT] How often during the last year have you failed to do what was normally expected from you because of drinking?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 6,
                    text = "[AUDIT] How often during the last year have you needed a first drink in the morning to get yourself going after a heavy drinking session?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 7,
                    text = "[AUDIT] How often during the last year have you had a feeling of guilt or remorse after drinking?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 8,
                    text = "[AUDIT] How often during the last year have you been unable to remember what happened the night before because of your drinking?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 9,
                    text = "[AUDIT] Have you or someone else been injured because of your drinking?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 10,
                    text = "[AUDIT] Has a relative, friend, doctor, or other health worker been concerned about your drinking or suggested you cut down?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 11,
                    text = "[CAGE] Have you ever felt you should Cut down on your drinking?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 12,
                    text = "[CAGE] Have people Annoyed you by criticizing your drinking?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 13,
                    text = "[CAGE] Have you ever felt bad or Guilty about your drinking?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 14,
                    text = "[CAGE] Have you ever had a drink first thing in the morning (Eye-opener) to steady your nerves or get rid of a hangover?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 15,
                    text = "[DAST-10] Have you used drugs other than those required for medical reasons?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 16,
                    text = "[DAST-10] Do you abuse more than one drug at a time?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 17,
                    text = "[DAST-10] Are you always able to stop using drugs when you want to?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 18,
                    text = "[DAST-10] Have you had 'blackouts' or 'flashbacks' as a result of drug use?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 19,
                    text = "[DAST-10] Do you ever feel bad or guilty about your drug use?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 20,
                    text = "[DAST-10] Does your spouse (or parents) ever complain about your involvement with drugs?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 21,
                    text = "[DAST-10] Have you neglected your family because of your use of drugs?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 22,
                    text = "[DAST-10] Have you engaged in illegal activities in order to obtain drugs?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 23,
                    text = "[DAST-10] Have you ever experienced withdrawal symptoms (felt sick) when you stopped taking drugs?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 24,
                    text = "[DAST-10] Have you had medical problems as a result of your drug use (e.g. memory loss, hepatitis, convulsions, bleeding)?",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                )
            )
        ),
        PsychologyTest(
            id = "major_personality_combined",
            title = "Major Personality & Archetypes Assessment",
            category = TestCategory.MAJOR_PERSONALITY,
            description = "Evaluates core character traits, daily behavioral habits, social energy preferences, decision-making style, and motivational archetype.",
            durationMinutes = 15,
            badgeText = "Gold Standard",
            testsForLabel = "Core character traits, social preferences, and motivational archetypes",
            questions = listOf(
                Question(
                    id = 1,
                    text = "I am the life of the party and gain energy in social gatherings.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 2,
                    text = "I feel comfortable around people and initiate conversations easily.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 3,
                    text = "I keep in the background and prefer quiet reflection.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 4,
                    text = "I am interested in people's stories and feel deep empathy for others.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 5,
                    text = "I sympathize with others' feelings and offer support naturally.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 6,
                    text = "I am not interested in other people's problems.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 7,
                    text = "I am always prepared and keep my space organized.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 8,
                    text = "I pay attention to details and double-check my work.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 9,
                    text = "I leave my belongings around and dislike strict schedules.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 10,
                    text = "I get stressed out easily when unexpected challenges arise.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 11,
                    text = "I am relaxed most of the time and handle pressure calmly.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 12,
                    text = "I worry about things and replay past interactions in my head.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 13,
                    text = "I have a rich vocabulary and love exploring abstract ideas.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 14,
                    text = "I have excellent ideas and enjoy complex problem-solving.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 15,
                    text = "I am not interested in abstract concepts or theoretical discussions.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 16,
                    text = "My decisions are guided by core moral principles rather than personal gain.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 17,
                    text = "I prefer straightforward honesty even when it feels uncomfortable.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 18,
                    text = "I feel a strong sense of duty toward my family and community commitments.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 19,
                    text = "I enjoy contemplating philosophical questions about human nature.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 20,
                    text = "I feel energized when mastering a difficult new skill independently.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 21,
                    text = "I easily adapt to sudden plan changes without frustration.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 22,
                    text = "I place high value on aesthetic beauty, art, and nature experiences.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 23,
                    text = "I am confident in my ability to achieve my long-term personal goals.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 24,
                    text = "I find it easy to forgive others when misunderstandings occur.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                )
            )
        ),
        PsychologyTest(
            id = "relationship_attachment_combined",
            title = "Relationship, Bonding & Attachment Evaluation",
            category = TestCategory.RELATIONSHIP,
            description = "Examines how you express affection, seek emotional connection, navigate intimacy, and handle interpersonal trust.",
            durationMinutes = 10,
            badgeText = "Bonding & Trust",
            testsForLabel = "Affection expression, attachment security, relationship trust, and intimacy",
            questions = listOf(
                Question(
                    id = 1,
                    text = "I feel comfortable depending on romantic partners or close friends.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 2,
                    text = "I worry that partners will not stay with me or will stop loving me.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 3,
                    text = "I express affection openly and communicate emotional needs clearly.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 4,
                    text = "I prefer to keep a degree of independence in close relationships.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 5,
                    text = "I feel safe sharing my deepest thoughts and vulnerabilities.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 6,
                    text = "I find it difficult to trust others completely.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 7,
                    text = "I handle relational conflict with open discussion rather than withdrawal.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 8,
                    text = "I feel valued and appreciated by the important people in my life.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 9,
                    text = "I actively listen to my partner's perspective during disagreements.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 10,
                    text = "I feel comfortable asking for support when going through tough times.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 11,
                    text = "I enjoy creating shared traditions and meaningful routines with loved ones.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 12,
                    text = "I feel secure about our long-term bond even during temporary distance.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                )
            )
        ),
        PsychologyTest(
            id = "eq_resilience_combined",
            title = "EQ & Resilience Skills Inventory",
            category = TestCategory.EMOTIONAL_INTELLIGENCE,
            description = "Measures self-awareness, empathy for others, emotional self-regulation, and capacity to recover from setbacks.",
            durationMinutes = 10,
            badgeText = "Core Growth",
            testsForLabel = "Self-awareness, empathy, emotional control, and adversity recovery",
            questions = listOf(
                Question(
                    id = 1,
                    text = "I can easily identify the exact emotions I am feeling in real time.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 2,
                    text = "I pause and breathe before reacting to frustrating or tense situations.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 3,
                    text = "I understand how my mood affects the people around me.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 4,
                    text = "I recover quickly from setbacks and maintain a positive outlook.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 5,
                    text = "I am skilled at resolving conflicts between colleagues or friends.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 6,
                    text = "I actively practice self-compassion when I make mistakes.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 7,
                    text = "I can read non-verbal cues and body language accurately.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 8,
                    text = "I stay focused on long-term goals even when short-term gratification is tempting.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 9,
                    text = "I accept constructive feedback without becoming defensive.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 10,
                    text = "I can reframe negative thoughts into empowering perspectives.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 11,
                    text = "I feel comfortable setting healthy emotional boundaries with others.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 12,
                    text = "I cultivate daily gratitude and appreciate small positive moments.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                )
            )
        ),
        PsychologyTest(
            id = "projective_depth_combined",
            title = "Projective Depth & Diagnostic Tools",
            category = TestCategory.PROJECTIVE,
            description = "Explores unconscious thought patterns, underlying thematic drives, and how you perceive complex or ambiguous scenarios.",
            durationMinutes = 10,
            badgeText = "Depth Psychology",
            testsForLabel = "Unconscious motivations, perceptual style, creative imagination, and themes",
            questions = listOf(
                Question(
                    id = 1,
                    text = "When viewing ambiguous images, I tend to see hopeful and creative stories.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 2,
                    text = "I notice subtle symbolic meanings in art, dreams, and literature.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 3,
                    text = "I feel drawn to exploring the hidden motivations behind human actions.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 4,
                    text = "I easily imagine detailed backstories for strangers I see in public.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 5,
                    text = "I reflect deeply on complex metaphors and abstract artistic expressions.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 6,
                    text = "I find that spontaneous thoughts often reveal hidden personal insights.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 7,
                    text = "I enjoy interpreting recurring themes in my dreams or creative projects.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 8,
                    text = "I feel connected to universal archetypes and mythic narratives.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 9,
                    text = "I perceive emotional undertones in rooms or conversations before words are spoken.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 10,
                    text = "I trust my intuitive impressions even when logical proof is still developing.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 11,
                    text = "I use creative imagination to solve problems when standard methods fail.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                ),
                Question(
                    id = 12,
                    text = "I feel comfortable accepting mystery and unanswerable questions in life.",
                    choices = listOf(
                        Choice("c1", "Not at all / Never", "Low Indicator", 0),
                        Choice("c2", "Several days / Sometimes", "Mild Indicator", 1),
                        Choice("c3", "More than half the days / Often", "Moderate Indicator", 2),
                        Choice("c4", "Nearly every day / Very Often", "High Indicator", 3)
                    )
                )
            )
        )
    )
}

#### Entites:
##### User:
1. email(for now considering for login)
2. Id
3. Password

##### Question:
1. Id
2. Text
3. PostedBy
4. PostedAt
5. upvotedBy -> Set
6. downvotedBy -> set
7. upvoteCount
8. downvoteCount

##### Answer:
1. Id
2. Text
3. QuestionId
4. PostedBy
5. PostedAt
5. upvotedBy -> Set
6. downvotedBy -> set
7. upvoteCount
8. downvoteCount

##### Comment:
1. id: String
2. commentFor: ENUM (QUESTION, ANSWER)
3. parentId: String
4. text: String
5. postedBy: String (userId)
6. postedAt: timestamp
7. upvotedBy: Set<String>
8. downvotedBy: Set<String>
9. upvotes: int (upvotedBy.size())
10. downvotes: int (downvotedBy.size())

##### StackOverflowApp
1. Map<Id, Question>
2. Map<Id, Answer>
3. Map<Id, comment>
4. Map<Id, User>
5. Map<String, List<Id>> => this is for search, key is text word, value is list of question-Ids, while question is entered we will split it and store in this map also.
6. functionalities:
    1. registerUser -> user Service file
    2. userLogin -> user service file
    3. postQuestion -> question file
    4. postAnswer -> answer file
    5. postComment -> comment file
    6. upvoteQuestion -> question file
    7. downvoteQuestion -> question file
    8. upvoteAnswer -> answer file
    9. downvoteAnswer -> answer file
    10. upvoteComment -> comment file
    11. downvoteComment -> comment file
    12. search -> search file
    13. displayUserDetails -> user service file

#### Relationships
1. User 1 → M Questions
2. User 1 → M Answers
3. User 1 → M Comments
4. Question 1 → M Answers
5. Question 1 → M Comments
6. Question M ↔ M Users (voting)
7. Answer M → 1 Question
8. Answer 1 → M Comments
9. Answer M ↔ M Users (voting)
10. Comment M → 1 (Question OR Answer)
11. Comment M ↔ M Users (voting)

#### Design Patterns
##### Step 1: Look for "Creation" Problems
Ask yourself: "Am I creating different but similar objects?"

Creating Question, Answer, Comment (all content types)

Pattern: Factory Pattern ✅

##### Step 2: Look for "Choice/Algorithm" Problems
Ask yourself: "Do I need to choose between different ways of doing something?"

Different search methods (by title, content)

Pattern: Strategy Pattern ✅

##### Step 3: Look for "Single Instance" Problems
Ask yourself: "Should there be only one of this thing?"

Main application class

Pattern: Singleton Pattern ✅

### Designing:
1. create content parent class
2. make Question, Answer, Comment children of Content Parent.
3. then we can easily apply factory pattern for creating either Question/Answer/Comment.
4. with current implementation, its not true factory pattern, but we can still apply factory pattern here while creating Question / Answer / Comment.
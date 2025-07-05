CREATE TABLE IF NOT EXISTS users (
                                     id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS diaries (
                                       id VARCHAR(255) PRIMARY KEY,
    date DATE NOT NULL,
    title VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    activity TEXT,
    amount DOUBLE,
    feeling VARCHAR(50),
    rating INT,
    notes TEXT
    );

CREATE TABLE IF NOT EXISTS user_diaries (
                                            user_id VARCHAR(255),
    diary_id VARCHAR(255),
    PRIMARY KEY (user_id, diary_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (diary_id) REFERENCES diaries(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS diary_participants (
                                                  diary_id VARCHAR(255),
    participant_name VARCHAR(255),
    PRIMARY KEY (diary_id, participant_name),
    FOREIGN KEY (diary_id) REFERENCES diaries(id) ON DELETE CASCADE
    );

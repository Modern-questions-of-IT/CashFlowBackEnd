CREATE TABLE Users (
    user_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Categories (
    category_id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES Users(user_id) ON DELETE CASCADE,
    name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Transactions (
    transaction_id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES Users(user_id) ON DELETE CASCADE,
    type VARCHAR(10) NOT NULL CHECK (type IN ('income', 'expense')),
    category_id INTEGER REFERENCES Categories(category_id) ON DELETE SET NULL,
    title VARCHAR(100),
    amount DECIMAL(10, 2) NOT NULL,
    date DATE NOT NULL,
    is_recurring BOOLEAN DEFAULT FALSE,
    frequency VARCHAR(20),
    next_occurrence DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Confirmations (
    confirmation_id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES Users(user_id) ON DELETE CASCADE,
    transaction_id INTEGER NOT NULL REFERENCES Transactions(transaction_id) ON DELETE CASCADE,
    confirmed BOOLEAN DEFAULT FALSE,
    confirmation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

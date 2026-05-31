CREATE TABLE IF NOT EXISTS users (
  user_id SERIAL PRIMARY KEY,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  email_address VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255),
  date_of_birth DATE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS address (
  address_id SERIAL PRIMARY KEY,
  user_id INT NOT NULL,
  street VARCHAR(255),
  city VARCHAR(100),
  state VARCHAR(100),
  postal_code VARCHAR(20),
  country VARCHAR(100),
  CONSTRAINT fk_user
    FOREIGN KEY(user_id)
    REFERENCES users(user_id)
    ON DELETE CASCADE
);

WITH new_user AS (
  INSERT INTO users (first_name, last_name, email_address, password, date_of_birth)
  VALUES ('Saravanan', 'Rajendran', 'sarvanhd@gmail.com', '', '1993-09-06')
  RETURNING user_id
)
INSERT INTO address (user_id, street, city, state, postal_code, country)
SELECT user_id, 'No 34 Dharmaraja street Srinivasapuram Guduvancheri', 'Chennai', 'Tamil Nadu', '603202', 'India'
FROM new_user;

CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_at = NOW();
  RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_user_updated_at
BEFORE UPDATE ON users
FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_column();
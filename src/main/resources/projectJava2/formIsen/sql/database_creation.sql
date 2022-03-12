CREATE TABLE IF NOT EXISTS person (
    idperson INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 
    lastname VARCHAR(45) NOT NULL,
    firstname VARCHAR(45) NOT NULL,
    nickname VARCHAR(45) NOT NULL,
    phone_number VARCHAR(15) NULL,
    address VARCHAR(200) NULL,
    email_address VARCHAR(150) NULL,
    birth_date DATE NULL,
    friend_list VARCHAR(200) NULL
    UNIQUE(phone_number, email_address));

CREATE INDEX 'lastname_index' ON 'person' ('lastname');
CREATE INDEX 'firstname_index' on 'person' ('firstname');
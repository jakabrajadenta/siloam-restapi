CREATE TABLE response_track (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    url VARCHAR,
    response_status VARCHAR,
    response_code VARCHAR,
    response_description VARCHAR
);
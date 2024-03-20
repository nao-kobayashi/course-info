create table if not exists COURSES(
    ID varchar primary key not null,
    NAME varchar not null,
    LENGTH int not null,
    URL varchar not null,
    NOTES varchar
);

DROP TABLE AHUser CASCADE CONSTRAINTS;
DROP TABLE AHBio CASCADE CONSTRAINTS;
DROP TABLE AHJob CASCADE CONSTRAINTS;
DROP TABLE AHApplication CASCADE CONSTRAINTS;

DROP SEQUENCE AHUser_id_seq;
DROP SEQUENCE AHBio_id_seq;
DROP SEQUENCE AHJob_id_seq;
DROP SEQUENCE AHApplication_id_seq;

CREATE SEQUENCE AHUser_id_seq
START WITH 1;

CREATE SEQUENCE AHBio_id_seq
START WITH 1;

CREATE SEQUENCE AHJob_id_seq
START WITH 1;

CREATE SEQUENCE AHApplication_id_seq
START WITH 1;

CREATE TABLE AHUser (
  userId        NUMBER(6) PRIMARY KEY,
  name          VARCHAR2(50),
  email         VARCHAR2(50),
  password      VARCHAR2(50),
  role          VARCHAR(50),
  userStatus        VARCHAR(50)
);

CREATE TABLE AHBio (
  bioId         NUMBER(6) PRIMARY KEY,
  userId        NUMBER(6),
  bio           VARCHAR2(1000),
  skills        VARCHAR2(500),
  CONSTRAINT userId_AHBio_fk FOREIGN KEY (userId) REFERENCES AHUser(userId)
);

CREATE TABLE AHJob (
  jobId           NUMBER(6) PRIMARY KEY,
  userId          NUMBER(6),
  datePosted      DATE,
  jobTitle        VARCHAR2(100),
  description     VARCHAR2(1000),
  jobStatus       VARCHAR2(20),
  CONSTRAINT userId_AHJob_fk FOREIGN KEY (userId) REFERENCES AHUser(userId)
);

CREATE TABLE AHApplication (
  applicationId       NUMBER(6) PRIMARY KEY,
  userId              NUMBER(6),
  jobId               NUMBER(6),
  dateApplied         DATE,
  applicationStatus   VARCHAR2(20),
  CONSTRAINT userId_AHApplication_fk FOREIGN KEY (userId) REFERENCES AHUser(userId),
  CONSTRAINT jobId_AHApplication_fk FOREIGN KEY (jobId) REFERENCES AHJob(jobId)
);
/* Data population for AHUSER */
INSERT INTO AHUser VALUES (AHUser_id_seq.nextval, 'Oliver', 'oliver@hotmail.co.uk', 'Oliver', 'EMPLOYER', 'ACTIVE');
INSERT INTO AHUser VALUES (AHUser_id_seq.nextval, 'Jack', 'jack@hotmail.co.uk','Jack', 'CANDIDATE', 'ACTIVE');
INSERT INTO AHUser VALUES (AHUser_id_seq.nextval, 'Charlie', 'charlie@hotmail.co.uk','Charlie','CANDIDATE', 'ACTIVE');
INSERT INTO AHUser VALUES (AHUser_id_seq.nextval, 'Harry', 'harry@hotmail.co.uk','Harry', 'EMPLOYER', 'ACTIVE');
INSERT INTO AHUser VALUES (AHUser_id_seq.nextval, 'Jacob', 'jacob@hotmail.co.uk','Jacob', 'EMPLOYER', 'ACTIVE');
INSERT INTO AHUser VALUES (AHUser_id_seq.nextval, 'George', 'george@hotmail.co.uk','George', 'CANDIDATE', 'ACTIVE');

/* Data population for AHBIO */
INSERT INTO AHBio VALUES (AHBio_id_seq.nextval, 2,  'A specialist in extortion and kidnapping is looking forward to kidnapping your most hated celebrity', 'Extortion, Kidnapping');
INSERT INTO AHBio VALUES (AHBio_id_seq.nextval, 3,  'The best strangler and ninja in town ready for his next assassination!','Strangling, smothering');
INSERT INTO AHBio VALUES (AHBio_id_seq.nextval, 6,  'If you are looking for someone good with knives, swords and anything with blades I am your best pick.','Knives, Razors, Swords, Axes');

/* Data population for AHJOB */
INSERT INTO AHJob VALUES (AHJob_id_seq.nextval, 1,  SYSDATE -1, 'Sniper wanted', 'We are seeking snipers able to take out high profile targets with ease and without detection.', 'ACTIVE');
INSERT INTO AHJob VALUES (AHJob_id_seq.nextval, 1,  SYSDATE -1, 'Creative Poison Administrator', 'If you are a good chemist and enjoy creating new poisons and potions this role might be just what you are looking for!', 'ACTIVE');
INSERT INTO AHJob VALUES (AHJob_id_seq.nextval, 4,  SYSDATE -1, 'Serial killer wanted urgently', 'I have a family of psychos to take out. Get in touch if interested.', 'ACTIVE');
INSERT INTO AHJob VALUES (AHJob_id_seq.nextval, 4,  SYSDATE -1, 'Professsional sniper', 'Professional sniper with excellent communication skills required for short term contract', 'INACTIVE');
INSERT INTO AHJob VALUES (AHJob_id_seq.nextval, 4,  SYSDATE -1, 'Silent assassin', 'A very quiet person or ninja required to send off to the other side a scaredy person suffering from insomnia.', 'INACTIVE');
INSERT INTO AHJob VALUES (AHJob_id_seq.nextval, 5,  SYSDATE -1, 'Snipers wanted', 'Snipers specialising in taking out politicians and annoying people please get in touch.', 'ACTIVE');

/*Data population for AHAPPLICATION */
INSERT INTO AHApplication VALUES (AHApplication_id_seq.nextval, 2, 1, SYSDATE -1, 'SUBMITTED');
INSERT INTO AHApplication VALUES (AHApplication_id_seq.nextval, 3, 2, SYSDATE -1, 'SUBMITTED');
INSERT INTO AHApplication VALUES (AHApplication_id_seq.nextval, 3, 3, SYSDATE -1, 'CANCELLED');
INSERT INTO AHApplication VALUES (AHApplication_id_seq.nextval, 6, 4, SYSDATE -1, 'CANCELLED');
INSERT INTO AHApplication VALUES (AHApplication_id_seq.nextval, 2, 6, SYSDATE -1, 'SUBMITTED');
INSERT INTO AHApplication VALUES (AHApplication_id_seq.nextval, 6, 6, SYSDATE -1, 'SUBMITTED');

COMMIT;
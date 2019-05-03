INSERT into skills (id,name,active) value (1,"Java programming",1);
INSERT into skills (id,name,active) value (2,"C# programming",1);
INSERT into skills (id,name,active) value (3,"Database design",1);

INSERT into candidates (id, first_Name, last_Name, date_of_birth, contact_number, email,active)
value (1,"Pera","Peric","1985-01-01","021456754","pera@gmail.com",1);
INSERT into candidates (id, first_Name, last_Name, date_of_birth, contact_number, email,active)
value (2,"Mika","Mikic","1995-02-02","061123487","mika@gmail.com",1);
INSERT into candidates (id, first_Name, last_Name, date_of_birth, contact_number, email,active)
value (3,"Zika","Zikic","1996-06-03","1457899","zika@gmail.com",1);

INSERT into possesses (job_candidate_id, skill_id) value (1,1);
INSERT into possesses (job_candidate_id, skill_id) value (1,2);
INSERT into possesses (job_candidate_id, skill_id) value (2,1);
INSERT into possesses (job_candidate_id, skill_id) value (2,2);
INSERT into possesses (job_candidate_id, skill_id) value (2,3);
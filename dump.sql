;             
CREATE USER IF NOT EXISTS "SA" SALT '4c6985efc67743b5' HASH '43bce2714b3cdd04b3dc3d598a86d191d944f6a6768cb8d4dfe671c448529d51' ADMIN;         
CREATE SEQUENCE "PUBLIC"."HIBERNATE_SEQUENCE" START WITH 2;   
CREATE MEMORY TABLE "PUBLIC"."CANVAS"(
    "ID" BIGINT NOT NULL,
    "CREATED" TIMESTAMP,
    "LAST_MODIFIED" TIMESTAMP,
    "VERSION" BIGINT
);         
ALTER TABLE "PUBLIC"."CANVAS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_7" PRIMARY KEY("ID");       
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.CANVAS;  
CREATE MEMORY TABLE "PUBLIC"."CANVAS_ASSIGNMENTS"(
    "CANVAS_ID" BIGINT NOT NULL,
    "DESCRIPTION" VARCHAR(255),
    "ID" BIGINT,
    "NAME" VARCHAR(255)
);          
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.CANVAS_ASSIGNMENTS;      
CREATE MEMORY TABLE "PUBLIC"."CANVAS_COURSES"(
    "CANVAS_ID" BIGINT NOT NULL,
    "ID" VARCHAR(255),
    "OPMERKINGEN" VARCHAR(255),
    "PROJECT_NAAM" VARCHAR(255),
    "TAAK_NAAM" VARCHAR(255)
); 
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.CANVAS_COURSES;          
CREATE MEMORY TABLE "PUBLIC"."SHAREPOINT"(
    "ID" BIGINT NOT NULL,
    "CREATED" TIMESTAMP,
    "LAST_MODIFIED" TIMESTAMP,
    "VERSION" BIGINT,
    "ABOUT_ME" TEXT,
    "CONTRIBUTIONS" VARCHAR(255),
    "DEPARTMENT" VARCHAR(255),
    "DISPLAY_NAME" VARCHAR(255),
    "EMAIL_ADRESS" VARCHAR(255),
    "FIRST_NAME" VARCHAR(255),
    "GOALS" TEXT,
    "LAST_NAME" VARCHAR(255),
    "OFFICE" VARCHAR(255),
    "PHOTO_URI" VARCHAR(255),
    "PROJECT_DESCRIPTIONS" VARCHAR(255),
    "SCHEDULE" VARCHAR(255),
    "TEL_LINK" VARCHAR(255),
    "TITLE" VARCHAR(255),
    "USERNAME" VARCHAR(255),
    "VIDEO_LINK" VARCHAR(255),
    "WORK_PHONE" VARCHAR(255)
);            
ALTER TABLE "PUBLIC"."SHAREPOINT" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_B" PRIMARY KEY("ID");   
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.SHAREPOINT;              
INSERT INTO "PUBLIC"."SHAREPOINT" VALUES
(1, TIMESTAMP '2022-04-07 14:43:27.199597', TIMESTAMP '2022-04-07 14:43:27.199597', 0, STRINGDECODE('<p><br></p><p>Sinds augustus 2021 voer ik, samen met Marcel Narings, IT Architect taken uit binnen FHICT, dit voor 2 dagen in de week.<br>Sinds mei 2019 ben ik projectleider van het ambitiethema Digital Excellence in het Open Up programma.<br>Van jan 2018 tot mei 2019 ben ik teamcoach geweest van het onderwijsteam Software. Ik ben sinds aug 2015 werkzaam bij Fontys ICT als docent software engineering. Daarvoor heb ik 3 jaar lesgegeven in de UCLL, de hogeschool Informatica van Leuven. Bij mijn lesgeven maak ik nog graag gebruik van mijn werkervaring in het consultancy bureau Xenit, een document management systeem installateur, en mijn 1 jaar onderzoekservaring als doctoraatsstudent bij de KUL. Ik ben afgestudeerd als Master Informatica, Artifici\u00eble Intelligentie in 2005 bij de KULeuven.</p>'), '<p>Operationalisering in vakken<br></p>', 'Algemeen', 'Veracx,Merel M.M.S.', NULL, 'Merel', '<p>Inhoudelijke expertise verdiepen in Java EE 7 en gelijkaardige frameworks. <br></p><p>Meer vakken geven in S6 en bijdragen aan software niveau verhoging in S3 en S4.</p><p>Vakken geven in Big Data specialisatie route. Verder uitbouwen van deze leerlijn.</p><p><span><span class=ms-profilevalue><span><span><span class=ms-profilevalue><span>PRIVEINFORMATIE</span></span></span><br><br>PRIVEINFORMATIE</span></span></span><br></p>', 'Veracx', 'R10 3.44', 'https://api.fhict.nl/pictures/I874529.jpg', '<p><br></p><p>- 2018 voorjaar start onderzoek</p><p>Scholingsplanning:</p><p>- 2017 nj: </p><p>&nbsp;- Big Data Machine Learning <br></p><p>&nbsp;- Basistraining ontdek autisme<br></p>', 'VERM61', '', 'Medewerker FHICT', 'I874529', '', '+31885074529');      
CREATE MEMORY TABLE "PUBLIC"."SHAREPOINT_AMBITIONS"(
    "SHAREPOINT_ID" BIGINT NOT NULL,
    "AMBITIONS" VARCHAR(255)
);  
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.SHAREPOINT_AMBITIONS;    
INSERT INTO "PUBLIC"."SHAREPOINT_AMBITIONS" VALUES
(1, 'Kennisinstelling'),
(1, 'Ondernemend gedrag'),
(1, 'Talent ontwikkeling');         
CREATE MEMORY TABLE "PUBLIC"."SHAREPOINT_CURRENT_PROJECTS"(
    "SHAREPOINT_ID" BIGINT NOT NULL,
    "CURRENT_PROJECTS" VARCHAR(255)
);    
-- 5 +/- SELECT COUNT(*) FROM PUBLIC.SHAREPOINT_CURRENT_PROJECTS;             
INSERT INTO "PUBLIC"."SHAREPOINT_CURRENT_PROJECTS" VALUES
(1, 'Wekelijkse formatieve feedback op leerdoelen in maatwerk S2 aan de hand van testen'),
(1, 'Ondernemendheid inbouwen in software ontwikkelingsvakken in semester 6 met rijkere leeromgeving en (optionele) opdrachten holistisch gedefinieerd'),
(1, 'Materiaalontwikkeling S3 voor Big Data afstudeerrichting'),
(1, 'Verhoging niveau software programmeervaardigheden in vak JCF4 door holistische leerdoelen'),
(1, 'SLB professionele beoordeling in de praktijk in startsemester en herstructureren materiaal in Canvas');           
CREATE MEMORY TABLE "PUBLIC"."SHAREPOINT_RESPONSIBILITIES"(
    "SHAREPOINT_ID" BIGINT NOT NULL,
    "RESPONSIBILITIES" VARCHAR(255)
);    
-- 9 +/- SELECT COUNT(*) FROM PUBLIC.SHAREPOINT_RESPONSIBILITIES;             
INSERT INTO "PUBLIC"."SHAREPOINT_RESPONSIBILITIES" VALUES
(1, 'Software Engineering'),
(1, 'Machine Learning'),
(1, 'Test Automation'),
(1, 'distributed systems'),
(1, 'Artificial Intelligence'),
(1, 'Talent Gericht Onderwijs'),
(1, 'Maatwerk'),
(1, 'Prikkelverwerking'),
(1, 'IT Architect'); 
CREATE MEMORY TABLE "PUBLIC"."SHAREPOINT_SKILLS"(
    "SHAREPOINT_ID" BIGINT NOT NULL,
    "SKILLS" VARCHAR(255)
);        
-- 8 +/- SELECT COUNT(*) FROM PUBLIC.SHAREPOINT_SKILLS;       
INSERT INTO "PUBLIC"."SHAREPOINT_SKILLS" VALUES
(1, 'Scherpe analytische blik'),
(1, 'Student gerichte opleiding'),
(1, 'Maatwerk'),
(1, 'Vakinhoudelijk'),
(1, 'Vakdidactisch'),
(1, 'Activerende werkvormen'),
(1, 'Passievol'),
(1, 'Humoristisch');               
CREATE MEMORY TABLE "PUBLIC"."TALENTEN_MARKTPLAATS"(
    "ID" BIGINT NOT NULL,
    "CREATED" TIMESTAMP,
    "LAST_MODIFIED" TIMESTAMP,
    "VERSION" BIGINT,
    "FUNCTIE" VARCHAR(255),
    "KENNIS" VARCHAR(255),
    "LINKED_IN" VARCHAR(255),
    "OPLEIDING" VARCHAR(255),
    "OVER_MIJ" VARCHAR(255),
    "ROL" VARCHAR(255),
    "TALENTEN" VARCHAR(255),
    "VRAAG" VARCHAR(255)
);    
ALTER TABLE "PUBLIC"."TALENTEN_MARKTPLAATS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_9" PRIMARY KEY("ID");         
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.TALENTEN_MARKTPLAATS;    
CREATE MEMORY TABLE "PUBLIC"."TALENTEN_MARKTPLAATS_INZET"(
    "TALENTEN_MARKTPLAATS_ID" BIGINT NOT NULL,
    "INZET" VARCHAR(255)
);      
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.TALENTEN_MARKTPLAATS_INZET;              
CREATE MEMORY TABLE "PUBLIC"."TALENTEN_MARKTPLAATS_PROJECTEN"(
    "TALENTEN_MARKTPLAATS_ID" BIGINT NOT NULL,
    "PROJECTEN" VARCHAR(255)
);              
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.TALENTEN_MARKTPLAATS_PROJECTEN;          
ALTER TABLE "PUBLIC"."CANVAS_COURSES" ADD CONSTRAINT "PUBLIC"."FK29XOIO894K08AFMGP0GLY8Y1S" FOREIGN KEY("CANVAS_ID") REFERENCES "PUBLIC"."CANVAS"("ID") NOCHECK;              
ALTER TABLE "PUBLIC"."SHAREPOINT_RESPONSIBILITIES" ADD CONSTRAINT "PUBLIC"."FKAHVJU43KF622LV4KE61ORANA9" FOREIGN KEY("SHAREPOINT_ID") REFERENCES "PUBLIC"."SHAREPOINT"("ID") NOCHECK;         
ALTER TABLE "PUBLIC"."CANVAS_ASSIGNMENTS" ADD CONSTRAINT "PUBLIC"."FK6GRJP0RDD9FHK2ILYJ32VF2UW" FOREIGN KEY("CANVAS_ID") REFERENCES "PUBLIC"."CANVAS"("ID") NOCHECK;          
ALTER TABLE "PUBLIC"."SHAREPOINT_CURRENT_PROJECTS" ADD CONSTRAINT "PUBLIC"."FK9I3ABPYL5TTNOD7CWPCXIKY41" FOREIGN KEY("SHAREPOINT_ID") REFERENCES "PUBLIC"."SHAREPOINT"("ID") NOCHECK;         
ALTER TABLE "PUBLIC"."SHAREPOINT_SKILLS" ADD CONSTRAINT "PUBLIC"."FKF1M4TUNW65GKUK7LKE7OD04WJ" FOREIGN KEY("SHAREPOINT_ID") REFERENCES "PUBLIC"."SHAREPOINT"("ID") NOCHECK;   
ALTER TABLE "PUBLIC"."TALENTEN_MARKTPLAATS_INZET" ADD CONSTRAINT "PUBLIC"."FKA7QMMO2KU82R4KC4I5W3KV3TJ" FOREIGN KEY("TALENTEN_MARKTPLAATS_ID") REFERENCES "PUBLIC"."TALENTEN_MARKTPLAATS"("ID") NOCHECK;      
ALTER TABLE "PUBLIC"."TALENTEN_MARKTPLAATS_PROJECTEN" ADD CONSTRAINT "PUBLIC"."FK6KVHLHSXD0BR0X5XOLGC4JKEX" FOREIGN KEY("TALENTEN_MARKTPLAATS_ID") REFERENCES "PUBLIC"."TALENTEN_MARKTPLAATS"("ID") NOCHECK;  
ALTER TABLE "PUBLIC"."SHAREPOINT_AMBITIONS" ADD CONSTRAINT "PUBLIC"."FKDNLY006W4OH39ETMD6VUT0D70" FOREIGN KEY("SHAREPOINT_ID") REFERENCES "PUBLIC"."SHAREPOINT"("ID") NOCHECK;

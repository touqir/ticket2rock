--  Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3 professionell" (dpunkt).
--  Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets f�r
--  Rockkonzerte auf Basis von EJB 3.0 und JavaServer Faces.
--
--  Copyright (C) 2006
--  Dierk Harbeck, Stefan M. Heldt, Oliver Ihns, Jochen J�rg und Holger Koschek
--
--  This program is free software; you can redistribute it and/or
--  modify it under the terms of the GNU General Public License
--  as published by the Free Software Foundation; either version 2
--  of the License, or (at your option) any later version.
--  
--  This program is distributed in the hope that it will be useful,
--  but WITHOUT ANY WARRANTY; without even the implied warranty of
--  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
--  GNU General Public License for more details.
--  
--  You should have received a copy of the GNU General Public License
--  along with this program; if not, write to the Free Software
--  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

INSERT INTO MUSIKGENRE (ID, NAME) VALUES (1, 'Alternative & Punk');
INSERT INTO MUSIKGENRE (ID, NAME) VALUES (2, 'Rock');

INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (1, 'M', 'Anthony Kiedis');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (2, 'M', 'Flea');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (3, 'M', 'John Frusciante');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (4, 'M', 'Chad Smith');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (5, 'B', 'Red Hot Chili Peppers');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (6, 'M', 'Chris Cornell');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (7, 'M', 'Tim Commerford');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (8, 'M', 'Brad Wilk');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (9, 'M', 'Tom Morello');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (10, 'B', 'Audioslave');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (11, 'M', 'Tom DeLonge');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (12, 'M', 'Mark Hoppus');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (13, 'M', 'Travis Barker');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (14, 'B', 'blink-182');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (15, 'M', 'David Kennedy');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (16, 'M', 'Atom Willard');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (17, 'M', 'Ryan Sinn');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (18, 'B', 'Angels and Airwaves');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (19, 'M', 'Billie Joe Armstrong');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (20, 'M', 'Mike Dirnt');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (21, 'M', 'Tr� Cool');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (22, 'B', 'Green Day');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (23, 'M', 'Serj Tankian');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (24, 'M', 'Daron Malakian');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (25, 'M', 'Shavo Odadjian');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (26, 'M', 'John Dolmayan');
INSERT INTO INTERPRET (ID, TYP, NAME) VALUES (27, 'B', 'System Of A Down');

INSERT INTO INTERPRET_INTERPRET (BANDS_ID, MUSIKER_ID) VALUES(5, 1);
INSERT INTO INTERPRET_INTERPRET (BANDS_ID, MUSIKER_ID) VALUES(5, 2);
INSERT INTO INTERPRET_INTERPRET (BANDS_ID, MUSIKER_ID) VALUES(5, 3);
INSERT INTO INTERPRET_INTERPRET (BANDS_ID, MUSIKER_ID) VALUES(5, 4);
INSERT INTO INTERPRET_INTERPRET (BANDS_ID, MUSIKER_ID) VALUES(10, 6);
INSERT INTO INTERPRET_INTERPRET (BANDS_ID, MUSIKER_ID) VALUES(10, 7);
INSERT INTO INTERPRET_INTERPRET (BANDS_ID, MUSIKER_ID) VALUES(10, 8);
INSERT INTO INTERPRET_INTERPRET (BANDS_ID, MUSIKER_ID) VALUES(10, 9);
INSERT INTO INTERPRET_INTERPRET (BANDS_ID, MUSIKER_ID) VALUES(14, 11);
INSERT INTO INTERPRET_INTERPRET (BANDS_ID, MUSIKER_ID) VALUES(14, 12);
INSERT INTO INTERPRET_INTERPRET (BANDS_ID, MUSIKER_ID) VALUES(14, 13);
INSERT INTO INTERPRET_INTERPRET (BANDS_ID, MUSIKER_ID) VALUES(18, 11);
INSERT INTO INTERPRET_INTERPRET (BANDS_ID, MUSIKER_ID) VALUES(18, 15);
INSERT INTO INTERPRET_INTERPRET (BANDS_ID, MUSIKER_ID) VALUES(18, 16);
INSERT INTO INTERPRET_INTERPRET (BANDS_ID, MUSIKER_ID) VALUES(18, 17);
INSERT INTO INTERPRET_INTERPRET (BANDS_ID, MUSIKER_ID) VALUES(22, 19);
INSERT INTO INTERPRET_INTERPRET (BANDS_ID, MUSIKER_ID) VALUES(22, 20);
INSERT INTO INTERPRET_INTERPRET (BANDS_ID, MUSIKER_ID) VALUES(22, 21);
INSERT INTO INTERPRET_INTERPRET (BANDS_ID, MUSIKER_ID) VALUES(27, 23);
INSERT INTO INTERPRET_INTERPRET (BANDS_ID, MUSIKER_ID) VALUES(27, 24);
INSERT INTO INTERPRET_INTERPRET (BANDS_ID, MUSIKER_ID) VALUES(27, 25);
INSERT INTO INTERPRET_INTERPRET (BANDS_ID, MUSIKER_ID) VALUES(27, 26);

INSERT INTO VERANSTALTUNGSORT (ID, NAME, ADRESSE, KAPAZITAET) VALUES (1, 'Colorline Arena', 'Hamburg', 30000);
INSERT INTO VERANSTALTUNGSORT (ID, NAME, ADRESSE, KAPAZITAET) VALUES (2, 'Festhalle', 'Frankfurt am Main', 40000);
INSERT INTO VERANSTALTUNGSORT (ID, NAME, ADRESSE, KAPAZITAET) VALUES (3, 'Hanns-Martin-Schleyer-Halle', 'Stuttgart', 20000);

INSERT INTO TOURNEE (ID, NAME, INTERPRET_ID) VALUES (1, 'Stadium Arcadium Tour', 5);

INSERT INTO KONZERT (ID, INTERPRET_ID, DATUM, VERANSTALTUNGSORT_ID, TOURNEE_ID, TICKETKONTINGENT) VALUES (1, NULL, '2006-11-24', 1, 1, 30000);
INSERT INTO KONZERT (ID, INTERPRET_ID, DATUM, VERANSTALTUNGSORT_ID, TOURNEE_ID, TICKETKONTINGENT) VALUES (2, NULL, '2006-11-26', 2, 1, 30000);
INSERT INTO KONZERT (ID, INTERPRET_ID, DATUM, VERANSTALTUNGSORT_ID, TOURNEE_ID, TICKETKONTINGENT) VALUES (3, NULL, '2006-11-27', 3, 1, 30000);

INSERT INTO ALBUM (ID, TITEL, ERSCHEINUNGSDATUM, INTERPRET_ID) VALUES (1, 'Stadium Arcadium, CD1: Jupiter', '2006', 5);
INSERT INTO ALBUM (ID, TITEL, ERSCHEINUNGSDATUM, INTERPRET_ID) VALUES (2, 'Stadium Arcadium, CD2: Mars', '2006', 5);
INSERT INTO ALBUM (ID, TITEL, ERSCHEINUNGSDATUM, INTERPRET_ID) VALUES (3, 'Audioslave', '2002', 10);
INSERT INTO ALBUM (ID, TITEL, ERSCHEINUNGSDATUM, INTERPRET_ID) VALUES (4, 'Greatest Hits', '2005', 14);
INSERT INTO ALBUM (ID, TITEL, ERSCHEINUNGSDATUM, INTERPRET_ID) VALUES (5, 'We Don''t Need To Whisper', '2006-05-23', 18);
INSERT INTO ALBUM (ID, TITEL, ERSCHEINUNGSDATUM, INTERPRET_ID) VALUES (6, 'American Idiot', '2004', 22);
INSERT INTO ALBUM (ID, TITEL, ERSCHEINUNGSDATUM, INTERPRET_ID) VALUES (7, 'Mesmerize', '2005', 27);
INSERT INTO ALBUM (ID, TITEL, ERSCHEINUNGSDATUM, INTERPRET_ID) VALUES (8, 'Hypnotize', '2005', 27);
INSERT INTO ALBUM (ID, TITEL, ERSCHEINUNGSDATUM, INTERPRET_ID) VALUES (9, 'Revelations', '2006-09-01', 10);

INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (1, 'Dani California', 5, 1, 1);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (2, 'Snow ((Hey Oh))', 5, 1, 1);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (3, 'Charlie', 5, 1, 1);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (4, 'Stadium Arcadium', 5, 1, 1);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (5, 'Hump de Bump', 5, 1, 1);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (6, 'She''s Only 18', 5, 1, 1);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (7, 'Slow Cheetah', 5, 1, 1);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (8, 'Torture Me', 5, 1, 1);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (9, 'Strip My Mind', 5, 1, 1);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (10, 'Especially In Michigan', 5, 1, 1);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (11, 'Warlocks', 5, 1, 1);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (12, 'C''mon Girl', 5, 1, 1);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (13, 'Wet Sand', 5, 1, 1);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (14, 'Hey', 5, 1, 1);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (15, 'Desecration Smile', 5, 1, 2);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (16, 'Tell Me Baby', 5, 1, 2);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (17, 'Hard to Concentrate', 5, 1, 2);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (18, '21st Century', 5, 1, 2);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (19, 'She Looks To Me', 5, 1, 2);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (20, 'Readymade', 5, 1, 2);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (21, 'If', 5, 1, 2);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (22, 'Make You Feel Better', 5, 1, 2);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (23, 'Animal Bar', 5, 1, 2);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (24, 'So Much I', 5, 1, 2);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (25, 'Storm In A Teacup', 5, 1, 2);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (26, 'We Believe', 5, 1, 2);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (27, 'Turn It Again', 5, 1, 2);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (28, 'Death of a Martian', 5, 1, 2);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (29, 'Cochise', 10, 2, 3);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (30, 'Show Me How To Live', 10, 2, 3);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (31, 'Gasoline', 10, 2, 3);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (32, 'What You Are', 10, 2, 3);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (33, 'Like A Stone', 10, 2, 3);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (34, 'Set It Off', 10, 2, 3);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (35, 'Shadow On The Sun', 10, 2, 3);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (36, 'I Am The Highway', 10, 2, 3);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (37, 'Exploder', 10, 2, 3);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (38, 'Hypnotize', 10, 2, 3);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (39, 'Bring Em Back Alive', 10, 2, 3);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (40, 'Light My Way', 10, 2, 3);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (41, 'Getaway Car', 10, 2, 3);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (42, 'The Last Remaining Light', 10, 2, 3);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (43, 'Carousel', 14, 1, 4);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (44, 'M+M''s', 14, 1, 4);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (45, 'Dammit', 14, 1, 4);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (46, 'Josie', 14, 1, 4);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (47, 'What''s My Age Again?', 14, 1, 4);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (48, 'All The Small Things', 14, 1, 4);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (49, 'Adam''s Song', 14, 1, 4);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (50, 'Man Overboard', 14, 1, 4);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (51, 'The Rock Show', 14, 1, 4);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (52, 'First Date', 14, 1, 4);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (53, 'Stay Together For The Kids', 14, 1, 4);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (54, 'Feeling This', 14, 1, 4);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (55, 'I Miss You', 14, 1, 4);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (56, 'Down', 14, 1, 4);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (57, 'Always', 14, 1, 4);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (58, 'Not Now', 14, 1, 4);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (59, 'Another Girl Another Planet', 14, 1, 4);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (60, 'I Won''t Be Home For Christmas', 14, 1, 4);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (61, 'Go (BBC Radio 1 Session)', 14, 1, 4);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (62, 'Valkyrie Missile', 18, 1, 5);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (63, 'Distraction', 18, 1, 5);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (64, 'Do It For Me Now', 18, 1, 5);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (65, 'The Adventure', 18, 1, 5);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (66, 'A Little''s Enough', 18, 1, 5);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (67, 'The War', 18, 1, 5);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (68, 'The Gift', 18, 1, 5);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (69, 'It Hurts', 18, 1, 5);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (70, 'Good Day', 18, 1, 5);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (71, 'Start The Machine', 18, 1, 5);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (72, 'American Idiot', 22, 1, 6);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (73, 'Jesus Of Suburbia / City Of The Damned / I Don''t Care / Dearly Beloved / Tales Of Another Broken Home', 22, 1, 6);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (74, 'Holiday', 22, 1, 6);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (75, 'Boulevard Of Broken Dreams', 22, 1, 6);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (76, 'Are We The Waiting', 22, 1, 6);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (77, 'St. Jimmy', 22, 1, 6);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (78, 'Give Me Novacaine', 22, 1, 6);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (79, 'She''s A Rebel', 22, 1, 6);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (80, 'Extraordinary Girl', 22, 1, 6);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (81, 'Letterbomb', 22, 1, 6);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (82, 'Wake Me Up When September Ends', 22, 1, 6);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (83, 'Homecoming / The Death Of St. Jimmy / East 12th St. / Nobody Likes You / Rock And Roll Girlfriend / We''re Coming Home Again', 22, 1, 6);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (84, 'Whatsername', 22, 1, 6);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (85, 'Soldier Side - Intro', 27, 1, 7);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (86, 'B.Y.O.B', 27, 1, 7);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (87, 'Revenga', 27, 1, 7);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (88, 'Cigaro', 27, 1, 7);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (89, 'Radio/Video', 27, 1, 7);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (90, 'This Cocaine Makes Me Feel Like I''m On This Song', 27, 1, 7);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (91, 'Violent Pornography', 27, 1, 7);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (92, 'Question!', 27, 1, 7);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (93, 'Sad Statue', 27, 1, 7);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (94, 'Old School Hollywood', 27, 1, 7);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (95, 'Lost In Hollywood', 27, 1, 7);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (96, 'Attack', 27, 1, 8);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (97, 'Dreaming', 27, 1, 8);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (98, 'Kill Rock''n''Roll', 27, 1, 8);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (99, 'Hypnotize', 27, 1, 8);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (100, 'Stealing Society', 27, 1, 8);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (101, 'Tentative', 27, 1, 8);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (102, 'U-Fig', 27, 1, 8);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (103, 'Holy Mountains', 27, 1, 8);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (104, 'Vicinity Of Obscenity', 27, 1, 8);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (105, 'She''s Like Heroin', 27, 1, 8);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (106, 'Lonely Day', 27, 1, 8);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (107, 'Soldier Side', 27, 1, 8);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (108, 'Revelations', 10, 2, 9);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (109, 'One And The Same', 10, 2, 9);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (110, 'Sound Of A Gun', 10, 2, 9);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (111, 'Until We Fall', 10, 2, 9);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (112, 'Original Fire', 10, 2, 9);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (113, 'Broken City', 10, 2, 9);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (114, 'Somedays', 10, 2, 9);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (115, 'Shape Of Things To Come', 10, 2, 9);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (116, 'Jewel Of The Summertime', 10, 2, 9);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (117, 'Wide Awake', 10, 2, 9);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (118, 'Nothing Left To Say But Goodbye', 10, 2, 9);
INSERT INTO SONG (ID, TITEL, INTERPRET_ID, GENRE_ID, ALBUM_ID) VALUES (119, 'Moth', 10, 2, 9);

INSERT INTO KUNDE (ID, EMAIL) VALUES (1, 'uschi.obermayer@groupie.net');

INSERT INTO TICKETRESERVIERUNG(ID, KONZERT_ID, KUNDE_ID, ANZAHL) VALUES (1, 1, 1, 2);

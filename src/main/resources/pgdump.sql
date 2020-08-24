--
-- PostgreSQL database dump
--

-- Dumped from database version 10.12 (Ubuntu 10.12-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.12 (Ubuntu 10.12-0ubuntu0.18.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: sessions; Type: TABLE; Schema: public; Owner: loginform
--

CREATE TABLE public.sessions (
    id text NOT NULL,
    user_id integer NOT NULL,
    creation_time timestamp without time zone NOT NULL,
    expiration_time timestamp without time zone NOT NULL
);


ALTER TABLE public.sessions OWNER TO loginform;

--
-- Name: users; Type: TABLE; Schema: public; Owner: loginform
--

CREATE TABLE public.users (
    id integer NOT NULL,
    username text NOT NULL,
    password text NOT NULL
);


ALTER TABLE public.users OWNER TO loginform;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: loginform
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO loginform;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: loginform
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: loginform
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: sessions; Type: TABLE DATA; Schema: public; Owner: loginform
--

COPY public.sessions (id, user_id, creation_time, expiration_time) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: loginform
--

COPY public.users (id, username, password) FROM stdin;
1	matthew	$2a$10$IbztCjCkuFzQMK6EUnvqa.KNXEckoMpZGPtAqHxHECuRMYQT4mO26
2	user	$2a$10$hit0veNHw6pLO1LesONIQuRJQtAhb6BVMU6NZl10JVofhBkxQ4ZXi
3	bob	$2a$10$7jU9Q79JKyZ4iwTtnRSJR.mGjVTHEwkaajvqsvvKB4Z4WPyHdWzym
4	steve	$2a$10$ASh1EDicgKW6A0nWMnKCqufTpC8ya8ppWZCMi7s4dOaJl3Tdv9BBi
5	jessica	$2a$10$dskzyAT5QXbBJFm6RyqFkO4Q54JUg/gYG0YiYQ0AEiitsiqSwLLhW
6	mary	$2a$10$yQL4aU4p4Hjb0sf9AQ18COnqwoO11ta/rBLPEJUTsEh.okWOjDWdO
7	john	$2a$10$xRidPSI1QRSpl3iQzeVeEuSLi1SRDQesVG83ib65zp.RVfQOxRAtm
8	james	$2a$10$NmrXRpZekRpkAPNAaARLyu23mLnGPK6lrSDT4DJzPIUa0gMKhJ5Ja
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: loginform
--

SELECT pg_catalog.setval('public.users_id_seq', 8, true);


--
-- Name: sessions sessions_pk; Type: CONSTRAINT; Schema: public; Owner: loginform
--

ALTER TABLE ONLY public.sessions
    ADD CONSTRAINT sessions_pk PRIMARY KEY (id);


--
-- Name: users users_pk; Type: CONSTRAINT; Schema: public; Owner: loginform
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pk PRIMARY KEY (id);


--
-- Name: sessions_id_uindex; Type: INDEX; Schema: public; Owner: loginform
--

CREATE UNIQUE INDEX sessions_id_uindex ON public.sessions USING btree (id);


--
-- Name: users_id_uindex; Type: INDEX; Schema: public; Owner: loginform
--

CREATE UNIQUE INDEX users_id_uindex ON public.users USING btree (id);


--
-- Name: users_username_uindex; Type: INDEX; Schema: public; Owner: loginform
--

CREATE UNIQUE INDEX users_username_uindex ON public.users USING btree (username);


--
-- PostgreSQL database dump complete
--


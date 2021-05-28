--
-- PostgreSQL database dump
--

-- Dumped from database version 12.6 (Ubuntu 12.6-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.6 (Ubuntu 12.6-0ubuntu0.20.04.1)

-- Started on 2021-05-28 01:49:00 +0530

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
-- TOC entry 2971 (class 1262 OID 33799)
-- Name: shopping_cart; Type: DATABASE; Schema: -; Owner: postgres

ALTER DATABASE shopping_cart OWNER TO postgres;

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
-- TOC entry 4 (class 2615 OID 33800)
-- Name: shopping_cart_schema; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA shopping_cart_schema;


ALTER SCHEMA shopping_cart_schema OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 203 (class 1259 OID 33801)
-- Name: items; Type: TABLE; Schema: shopping_cart_schema; Owner: postgres
--

CREATE TABLE shopping_cart_schema.items (
    item_code character varying(10) NOT NULL,
    item_name character varying(100),
    item_price real
);


ALTER TABLE shopping_cart_schema.items OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 33806)
-- Name: meta_data; Type: TABLE; Schema: shopping_cart_schema; Owner: postgres
--

CREATE TABLE shopping_cart_schema.meta_data (
    name character varying(100) NOT NULL,
    value character varying
);


ALTER TABLE shopping_cart_schema.meta_data OWNER TO postgres;

--
-- TOC entry 2837 (class 2606 OID 33805)
-- Name: items items_pkey; Type: CONSTRAINT; Schema: shopping_cart_schema; Owner: postgres
--

ALTER TABLE ONLY shopping_cart_schema.items
    ADD CONSTRAINT items_pkey PRIMARY KEY (item_code);


--
-- TOC entry 2839 (class 2606 OID 33813)
-- Name: meta_data meta_data_pkey; Type: CONSTRAINT; Schema: shopping_cart_schema; Owner: postgres
--

ALTER TABLE ONLY shopping_cart_schema.meta_data
    ADD CONSTRAINT meta_data_pkey PRIMARY KEY (name);

--
-- TOC entry 2966 (class 0 OID 33801)
-- Dependencies: 203
-- Data for Name: items; Type: TABLE DATA; Schema: shopping_cart_schema; Owner: postgres
--

INSERT INTO shopping_cart_schema.items (item_code, item_name, item_price) VALUES ('HC', 'horseshoe-carton', 825);
INSERT INTO shopping_cart_schema.items (item_code, item_name, item_price) VALUES ('PEC', 'penguin-ears-carton', 175);


--
-- TOC entry 2967 (class 0 OID 33806)
-- Dependencies: 204
-- Data for Name: meta_data; Type: TABLE DATA; Schema: shopping_cart_schema; Owner: postgres
--

INSERT INTO shopping_cart_schema.meta_data (name, value) VALUES ('horseshoe-carton-count', '5');
INSERT INTO shopping_cart_schema.meta_data (name, value) VALUES ('penguin-ears-carton-count', '20');
INSERT INTO shopping_cart_schema.meta_data (name, value) VALUES ('single-unit-rate', '1.3');
INSERT INTO shopping_cart_schema.meta_data (name, value) VALUES ('max-cartons-discount', '3');
INSERT INTO shopping_cart_schema.meta_data (name, value) VALUES ('cartons-discount-percentage', '0.10');


-- Completed on 2021-05-28 01:49:00 +0530

--
-- PostgreSQL database dump complete
--

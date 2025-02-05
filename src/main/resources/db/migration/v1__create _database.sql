--
-- PostgreSQL database dump
--

-- Dumped from database version 12.20
-- Dumped by pg_dump version 16.4

-- Started on 2025-02-05 00:01:35

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
-- TOC entry 6 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 222 (class 1255 OID 75221)
-- Name: actualizar_cantidad_tickets_postaccion(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.actualizar_cantidad_tickets_postaccion() RETURNS trigger
    LANGUAGE plpgsql
    AS $$DECLARE
    cantidad BIGINT := 0;
BEGIN
    -- Contar cuántos boletos hay para el evento del nuevo ticket insertado
    SELECT COUNT(*)
    INTO cantidad
    FROM tickets
    WHERE id_evento = NEW.id_evento;

    -- Mensaje de depuración (opcional)
    RAISE NOTICE 'Por ahora todo bien. Boletos vendidos: %', cantidad;

    -- Actualizar el número de boletos vendidos en la tabla eventos
    UPDATE eventos
    SET boletos_vendidos = cantidad
    WHERE id_evento = NEW.id_evento;

    RETURN NEW;
End;$$;


ALTER FUNCTION public.actualizar_cantidad_tickets_postaccion() OWNER TO postgres;

--
-- TOC entry 221 (class 1255 OID 75214)
-- Name: cantidad_tickets_preInsertar_evento(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public."cantidad_tickets_preInsertar_evento"() RETURNS trigger
    LANGUAGE plpgsql
    AS $$Begin
	New.boletos_vendidos := 0;
	return new;
End;$$;


ALTER FUNCTION public."cantidad_tickets_preInsertar_evento"() OWNER TO postgres;

--
-- TOC entry 207 (class 1255 OID 75219)
-- Name: cargar_cantidad_tickets(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.cargar_cantidad_tickets() RETURNS trigger
    LANGUAGE plpgsql
    AS $$DECLARE
	cantidad int := 0;
Begin
	Select boletos_vendidos into cantidad
	From eventos Where id_evento = NEW.id_evento;
	New.boletos_vendidos := cantidad;
	return new;
End;$$;


ALTER FUNCTION public.cargar_cantidad_tickets() OWNER TO postgres;

--
-- TOC entry 208 (class 1255 OID 75205)
-- Name: comprobar_tickets(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.comprobar_tickets() RETURNS trigger
    LANGUAGE plpgsql
    AS $$Declare
	cantidad bigint:= 0;
	evento RECORD;
Begin
	Select * From eventos into evento
	Where New.id_evento = eventos.id_evento;
	if(evento.cantidad_boletos = evento.boletos_vendidos) then
		Raise Exception 'No se pueden comprar más boletos para este evento';
	else
		return New;
	End if;
End$$;


ALTER FUNCTION public.comprobar_tickets() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 203 (class 1259 OID 75152)
-- Name: eventos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.eventos (
    id_evento bigint NOT NULL,
    nombre character varying(255) NOT NULL,
    fecha_inicio timestamp with time zone NOT NULL,
    fecha_fin timestamp with time zone NOT NULL,
    descripcion character varying(400) NOT NULL,
    cantidad_boletos bigint NOT NULL,
    boletos_vendidos bigint NOT NULL,
    estado boolean NOT NULL
);


ALTER TABLE public.eventos OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 75155)
-- Name: eventos_id_evento_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.eventos_id_evento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.eventos_id_evento_seq OWNER TO postgres;

--
-- TOC entry 2856 (class 0 OID 0)
-- Dependencies: 204
-- Name: eventos_id_evento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.eventos_id_evento_seq OWNED BY public.eventos.id_evento;


--
-- TOC entry 202 (class 1259 OID 75142)
-- Name: flyway_schema_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.flyway_schema_history OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 75182)
-- Name: tickets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tickets (
    id_tickets bigint NOT NULL,
    codigo character varying(300) NOT NULL,
    fecha_canjeo timestamp with time zone,
    is_canjeo boolean NOT NULL,
    id_evento bigint NOT NULL
);


ALTER TABLE public.tickets OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 75180)
-- Name: tickets_id_tickets_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tickets_id_tickets_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tickets_id_tickets_seq OWNER TO postgres;

--
-- TOC entry 2857 (class 0 OID 0)
-- Dependencies: 205
-- Name: tickets_id_tickets_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tickets_id_tickets_seq OWNED BY public.tickets.id_tickets;


--
-- TOC entry 2704 (class 2604 OID 75157)
-- Name: eventos id_evento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.eventos ALTER COLUMN id_evento SET DEFAULT nextval('public.eventos_id_evento_seq'::regclass);


--
-- TOC entry 2705 (class 2604 OID 75185)
-- Name: tickets id_tickets; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets ALTER COLUMN id_tickets SET DEFAULT nextval('public.tickets_id_tickets_seq'::regclass);


--
-- TOC entry 2846 (class 0 OID 75152)
-- Dependencies: 203
-- Data for Name: eventos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.eventos (id_evento, nombre, fecha_inicio, fecha_fin, descripcion, cantidad_boletos, boletos_vendidos, estado) FROM stdin;
\.


--
-- TOC entry 2845 (class 0 OID 75142)
-- Dependencies: 202
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
\.


--
-- TOC entry 2849 (class 0 OID 75182)
-- Dependencies: 206
-- Data for Name: tickets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tickets (id_tickets, codigo, fecha_canjeo, is_canjeo, id_evento) FROM stdin;
\.


--
-- TOC entry 2858 (class 0 OID 0)
-- Dependencies: 204
-- Name: eventos_id_evento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.eventos_id_evento_seq', 8, true);


--
-- TOC entry 2859 (class 0 OID 0)
-- Dependencies: 205
-- Name: tickets_id_tickets_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tickets_id_tickets_seq', 39, true);


--
-- TOC entry 2710 (class 2606 OID 75165)
-- Name: eventos eventos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.eventos
    ADD CONSTRAINT eventos_pkey PRIMARY KEY (id_evento);


--
-- TOC entry 2707 (class 2606 OID 75150)
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- TOC entry 2712 (class 2606 OID 75187)
-- Name: tickets tickets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_pkey PRIMARY KEY (id_tickets);


--
-- TOC entry 2714 (class 2606 OID 75199)
-- Name: tickets unik_tickets_codigo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT unik_tickets_codigo UNIQUE (codigo);


--
-- TOC entry 2708 (class 1259 OID 75151)
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);


--
-- TOC entry 2717 (class 2620 OID 75222)
-- Name: tickets Actualizar Valores de los tickets; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER "Actualizar Valores de los tickets" AFTER INSERT OR DELETE OR UPDATE ON public.tickets FOR EACH ROW EXECUTE FUNCTION public.actualizar_cantidad_tickets_postaccion();


--
-- TOC entry 2716 (class 2620 OID 75215)
-- Name: eventos Asignar Cantidad Eventos Previo Insertar; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER "Asignar Cantidad Eventos Previo Insertar" BEFORE INSERT ON public.eventos FOR EACH ROW EXECUTE FUNCTION public."cantidad_tickets_preInsertar_evento"();


--
-- TOC entry 2718 (class 2620 OID 75206)
-- Name: tickets Comprobar disponibilidad de tickets; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER "Comprobar disponibilidad de tickets" BEFORE INSERT ON public.tickets FOR EACH ROW EXECUTE FUNCTION public.comprobar_tickets();


--
-- TOC entry 2715 (class 2606 OID 75188)
-- Name: tickets fk_tickets_eventos_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT fk_tickets_eventos_id FOREIGN KEY (id_evento) REFERENCES public.eventos(id_evento) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2855 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2025-02-05 00:01:36

--
-- PostgreSQL database dump complete
--


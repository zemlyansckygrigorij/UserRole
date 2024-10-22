-- SEQUENCE: public.user_role_id_seq

-- DROP SEQUENCE IF EXISTS public.user_role_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.user_role_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.user_role_id_seq
    OWNED BY public.user_role.id;

ALTER SEQUENCE public.user_role_id_seq
    OWNER TO postgres;


-- Table: public.user_role

-- DROP TABLE IF EXISTS public.user_role;

CREATE TABLE IF NOT EXISTS public.user_role
(
    id integer NOT NULL DEFAULT nextval('user_role_id_seq'::regclass),
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT user_role_pkey PRIMARY KEY (id),
    CONSTRAINT user_role_role_id_fkey FOREIGN KEY (role_id)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_role_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user_role
    OWNER to postgres;
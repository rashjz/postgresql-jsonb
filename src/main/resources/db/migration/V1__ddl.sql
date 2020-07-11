
CREATE TABLE IF NOT EXISTS public.employee
(
    "key"      varchar(255) NOT NULL,
    value      jsonb        NOT NULL
);

ALTER TABLE public.employee
    ADD CONSTRAINT pk_employee_object PRIMARY KEY (key);
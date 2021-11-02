CREATE TABLE review_customer_entity (
    id character varying(255) NOT NULL,
    customer_number integer,
    first_name character varying(255),
    last_name character varying(255),
    profile_pic_url character varying(255)
);
ALTER TABLE ONLY review_customer_entity ADD CONSTRAINT review_customer_entity_pkey PRIMARY KEY (id);

CREATE TABLE review_entity (
    id character varying(255) NOT NULL,
    created_at timestamp without time zone NOT NULL,
    customer_id character varying(255) NOT NULL,
    locale character varying(255) NOT NULL,
    merchant_id character varying(255) NOT NULL,
    message text NOT NULL,
    rating integer NOT NULL,
    reservation_datetime timestamp without time zone NOT NULL,
    reservation_id character varying(255) NOT NULL,
    status character varying(255) NOT NULL,
    updated_at timestamp without time zone NOT NULL
);
ALTER TABLE ONLY review_entity ADD CONSTRAINT review_entity_pkey PRIMARY KEY (id);
CREATE INDEX review_entity_customer_id_status ON review_entity USING btree (customer_id);
CREATE INDEX review_entity_merchant_id_status ON review_entity USING btree (merchant_id, status);
CREATE INDEX review_entity_merchant_id_status_datetime_desc ON review_entity USING btree (merchant_id, status, reservation_datetime DESC);
CREATE INDEX review_entity_merchant_id_status_rating_asc ON review_entity USING btree (merchant_id, status, rating ASC);
CREATE INDEX review_entity_merchant_id_status_rating_desc ON review_entity USING btree (merchant_id, status, rating DESC);
CREATE INDEX review_entity_merchant_id_status_rating_desc_createdat_desc ON review_entity USING btree (merchant_id, status, rating DESC, created_at DESC);
CREATE INDEX review_entity_merchant_id_status_updatedat_desc ON review_entity USING btree (merchant_id, status, updated_at DESC);

CREATE TABLE review_merchant_entity (
    id character varying(255) NOT NULL,
    landscape text,
    slider text,
    name character varying(255),
    restaurant_number integer,
    slug character varying(255)
);
ALTER TABLE ONLY review_merchant_entity ADD CONSTRAINT review_merchant_entity_pkey PRIMARY KEY (id);
ALTER TABLE ONLY review_merchant_entity ADD CONSTRAINT uk_restaurant_number UNIQUE (restaurant_number);

CREATE TABLE review_reservation_entity (
    id character varying(255) NOT NULL,
    guest_count integer NOT NULL,
    created_at timestamp without time zone NOT NULL,
    reservation_number integer NOT NULL,
    starts_at timestamp without time zone NOT NULL
);
ALTER TABLE ONLY review_reservation_entity ADD CONSTRAINT review_reservation_entity_pkey PRIMARY KEY (id);
CREATE INDEX reservation_entity_startsat_desc ON review_reservation_entity USING btree (starts_at DESC);
create table roles (
  id bigserial primary key,
  name varchar(20) constraint ukofx66keruapi6vyqpv6f2or37 unique
);
create table users (
  id bigserial primary key,
  created_at timestamp not null DEFAULT now(),
  updated_at timestamp not null DEFAULT now(),
  account_code varchar(50) constraint ukk2avsow52g6ou3l7gsgb4a9vr unique,
  email varchar(50) constraint uk6dotkott2kjsp8vw4d0m25fb7 unique,
  first_name varchar(50),
  last_name varchar(50),
  password varchar(120),
  phone varchar(50),
  username varchar(20) constraint ukr43af9ap4edm43mmtq01oddj6 unique,
  user_updated_id bigint constraint fk2mno33fkmll013pbqsm2ict1a references users,
  user_created_id bigint constraint fkswmf0x02771u8pqfx791fd1qc references users
);
create table device (
  id bigserial primary key,
  created_at timestamp not null DEFAULT now(),
  updated_at timestamp not null DEFAULT now(),
  location varchar(255),
  name varchar(255),
  status boolean not null,
  token varchar(255),
  type varchar(255),
  uuid varchar(255) constraint uklcsquslb3ig7l6wsbwijoet4a unique,
  user_updated_id bigint constraint fkoedk068xviddbprhalkssxdqe references users,
  user_created_id bigint constraint fktn37mkrrlff5e966rqhda1r0m references users
);
create table html_pages (
  id bigserial primary key,
  created_at timestamp not null DEFAULT now(),
  updated_at timestamp not null DEFAULT now(),
  html text not null,
  url varchar(255) not null constraint uk80rbjty49nivx37a0fdjoyku unique,
  visible_for_moderators boolean DEFAULT true,
  visible_for_users boolean DEFAULT true,
  visible_on_nav boolean DEFAULT true,
  user_updated_id bigint constraint fkeh04tmclnjy8tto1l2gwm4imt references users,
  user_created_id bigint constraint fk9b5b81k6j8gnmgfcyu6c10rxo references users
);
create table user_roles (
  user_id bigint not null constraint fkhfh9dx7w3ubf1co1vdev94g3f references users,
  role_id bigint not null constraint fkh8ciramu9cc9q3qcqiv4ue8a6 references roles,
  primary key (user_id, role_id)
);
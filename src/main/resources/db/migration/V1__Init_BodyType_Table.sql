create table bodytypes(
                         id bigserial not null ,
                         name varchar(20) not null ,
                         created_by int8 null,
                         created_date timestamp not null default CURRENT_TIMESTAMP,
                         updated_by int8 null,
                         updated_date timestamp null,
                         deleted_by int8 null,
                         deleted_date timestamp null ,
                         is_deleted bit not null default bit'0',
                         constraint pk_bodytype primary key (id),
                         constraint fk_bodytype_created_by foreign key(created_by) references users(id),
                         constraint fk_bodytype_updated_by foreign key(updated_by) references users(id),
                         constraint fk_bodytype_deleted_by foreign key(deleted_by) references users(id)

);
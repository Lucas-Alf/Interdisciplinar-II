--EXEMPLO: select conta.id, conta.descricao, ativopassivo(conta.id) from conta
create OR replace function ativopassivo(integer) RETURNS text
AS
'select ativopassivo from (
WITH RECURSIVE conts (id, level, descricao, name_path) AS (
    SELECT  id, 0, descricao, ARRAY[cast(descricao as text)]
    FROM    conta
    WHERE   contapai is null

    UNION ALL

    SELECT  p.id, t0.level + 1, p.descricao, ARRAY_APPEND(t0.name_path, cast(p.descricao as text))
    FROM    conta p
            INNER JOIN conts t0 ON t0.id = p.contapai
)
SELECT  id, name_path[1] AS ativopassivo
FROM    conts) as dados where id = $1;'
LANGUAGE SQL
    IMMUTABLE
    RETURNS NULL ON NULL INPUT;
    
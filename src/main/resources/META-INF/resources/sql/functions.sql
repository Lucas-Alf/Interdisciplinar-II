--EXEMPLO: select conta.id, conta.descricao, ativopassivo(conta.id) from conta
create OR replace function ativopassivo(integer) RETURNS text
AS
'select descricao as ativopassivo from conta where id = (
select (case when contatataraavo is null or contatataraavo = 0
	then (case when contabisaavo is null or contabisaavo = 0
		then (case when contaavo is null or contaavo = 0
			then (case when contapai is null or contapai = 0
				then id
				else contapai end)
			else contaavo end)
		else contabisaavo end)
	else contatataraavo end) as ultima
from (
WITH RECURSIVE contas as (
	select 
		pai.id,
		pai.contapai,
		0 as contaavo,
		0 as contabisaavo,
		0 as contatataraavo,
		pai.descricao
	from conta as pai where pai.contapai is null
		union 
	select 
		filha.id,
		filha.contapai,
		c.contapai as contaavo,
		bisa.contapai as contabisaavo,
		contatataraavo.contapai as contatataraavo,
		filha.descricao
	from conta as filha
		inner join contas c on c.id = filha.contapai
		left  join conta as bisa on bisa.id = c.contapai
		left  join conta as contatataraavo on contatataraavo.id = bisa.contapai
) select * from contas
	where id = $1
	order by id
) as dados);'
LANGUAGE SQL
    IMMUTABLE
    RETURNS NULL ON NULL INPUT;
    
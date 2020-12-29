--Нужно написать запрос, который получит список всех заяков и количество подтвердивших участников.
select meeting.name, count(meeting_users.status) as participants
from meeting
left join meeting_users on meeting.id = meeting_users.meeting_id
where meeting_users.status = true
group by meeting.name
order by name;

--Нужно получить все совещания, где не было ни одной заявки на посещения
select meeting.name, count(meeting_users.status) as participants
from meeting
left join meeting_users on meeting.id = meeting_users.meeting_id
group by meeting.name
having count(meeting_users.status) = 0
order by name;
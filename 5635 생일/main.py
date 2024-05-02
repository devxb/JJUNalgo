line = int(input())

peoples = []


class People:
    def __init__(self, name, birthday):
        self.name = name
        self.birthday = birthday


for i in range(0, line):
    people_info = input().split(" ")
    if int(people_info[2]) < 10:
        people_info[2] = "0" + people_info[2]

    if int(people_info[1]) < 10:
        people_info[1] = "0" + people_info[1]

    birthday = int(people_info[3] + people_info[2] + people_info[1])
    peoples.append(People(people_info[0], birthday))

peoples.sort(key=lambda people: people.birthday)

for i in peoples:
    print("%s, %s" % (i.name, i.birthday))

print("%s\n%s" % (peoples[len(peoples) - 1].name, peoples[0].name))

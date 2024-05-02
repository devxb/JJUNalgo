testcase = int(input())


class University:

    def __init__(self, name, drink):
        self.name = name
        self.drink = drink


for i in range(0, testcase):
    univs = []
    univ_count = int(input())
    for j in range(0, univ_count):
        univ = input().split(" ")
        univs.append(University(univ[0], int(univ[1])))

    univs.sort(key=lambda university: university.drink, reverse=True)
    print(univs[0].name)

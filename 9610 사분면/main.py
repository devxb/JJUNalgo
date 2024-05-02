class Coordinate:
    def __init__(self, is_in_lambda):
        self.count = 0
        self.is_in_lambda = is_in_lambda

    def increase_count(self, y, x):
        if self.is_in_lambda(y, x):
            self.count += 1


q1_coordinate = Coordinate(lambda y, x: y > 0 and x > 0)
q2_coordinate = Coordinate(lambda y, x: y > 0 > x)
q3_coordinate = Coordinate(lambda y, x: y < 0 and x < 0)
q4_coordinate = Coordinate(lambda y, x: y < 0 < x)
axis_coordinate = Coordinate(lambda y, x: y == 0 or x == 0)

line = int(input())

for i in range(0, line):
    coordinate = input().split(" ")
    q1_coordinate.increase_count(int(coordinate[1]), int(coordinate[0]))
    q2_coordinate.increase_count(int(coordinate[1]), int(coordinate[0]))
    q3_coordinate.increase_count(int(coordinate[1]), int(coordinate[0]))
    q4_coordinate.increase_count(int(coordinate[1]), int(coordinate[0]))
    axis_coordinate.increase_count(int(coordinate[1]), int(coordinate[0]))

print("Q1: %d" % q1_coordinate.count)
print("Q2: %d" % q2_coordinate.count)
print("Q3: %d" % q3_coordinate.count)
print("Q4: %d" % q4_coordinate.count)
print("AXIS: %d" % axis_coordinate.count)

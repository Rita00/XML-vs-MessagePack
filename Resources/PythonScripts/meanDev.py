import statistics


def meanDev(in_file, out_file):
    f = open(in_file, 'r')
    all_times = list()
    while True:
        time = f.readline()
        if time == '':
            break
        all_times.append(int(time))
    mean = statistics.mean(all_times)
    std = statistics.stdev(all_times)
    f_out = open(out_file, 'a')
    in_file = in_file.replace("TimeFiles/", '')
    f_out.write("%s\n" % in_file.replace('.txt', '').upper())
    f_out.write("mean: %.2f\n" % mean)
    f_out.write("std: %.2f\n" % std)
    f_out.write("std percentage: %.2f\n\n" % (std / mean * 100))

    print("mean: %.2f" % mean)
    print("std: %.2f" % std)
    print("std percentage: %.2f" % (std / mean * 100))
    f.close()
    f_out.close()


if __name__ == '__main__':
    for i in range(2, 11):
        num_owners = i * 10000
        listFiles = ["TimeFiles/marshal_" + str(num_owners) + ".txt", "TimeFiles/unmarshal_" + str(num_owners) + ".txt",
                     "TimeFiles/deserialization_" + str(num_owners) + ".txt",
                     "TimeFiles/serialization_" + str(num_owners) + ".txt"]

        output_file = "Stats/stats_" + str(num_owners) + ".txt"
        for i in listFiles:
            meanDev(i, output_file)

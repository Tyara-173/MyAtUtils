import math
import itertools
n = int(input())
m = math.perm(n)
a = [[0 for _ in range(m)] for __ in range(m)]
for i in range(m):
    a[i] = list(map(int,input().split()))
p = list(itertools.permutations(range(n),n))
ans = list()
for v in list(itertools.permutations(range(m),m)):
    ok = True
    for i in range(m):
        for j in range(m):
            temp = []
            for k in range(n):
                temp.append(p[v[i]][p[v[j]][k]])
                ok &= temp[k] == p[v[a[i][j]-1]][k]
            if not ok:
                break
        if not ok:
            break
    if ok:
        ans.append(v)

print(len(ans))
for i in ans:
    t = []
    for j in i:
        t.append(j+1)
    print(*t)
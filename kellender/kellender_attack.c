#include <stdio.h>
#include <sys/resource.h>

int main(){
	struct rlimit limit;
	limit.rlim_cur = 100;
    limit.rlim_max = 100;
	setrlimit(RLIMIT_FSIZE, &limit);
	FILE *fp = fopen("bad.txt", "w");
	fprintf(fp, "I'm free!\n");
	fclose(fp);
	return 0;
}

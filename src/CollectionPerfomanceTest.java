import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CollectionPerfomanceTest {
	public static void main(String args[]){
		List <Integer>  arrayList = new ArrayList<> ();
		List <Integer> linkedList = new LinkedList<>();

		perfomanceArrayTest(arrayList, 1);
		for(int N=10;N<1_000_000;N*=10){
			printArrayResults((perfomanceArrayTest(arrayList, N)),N,1);
			printArrayResults((perfomanceArrayTest(linkedList, N)),N,2);
			System.out.println();
		}
		
		Set <Integer> hashSet = new HashSet<>();
		Set <Integer> treeSet = new TreeSet<>();
		
		perfomanceSetTest(treeSet, 1);
		for(int N=10;N<1_000_001;N*=10){
			printSetResults((perfomanceSetTest(hashSet, N)),N,1);
			printSetResults((perfomanceSetTest(treeSet, N)),N,2);
			System.out.println();
		}		
}

	public static void printArrayResults(long [] times, int N, int line){
		if(line==1){
			System.out.println("List");
			System.out.printf("N = %d:\t us to\n Insert\t\t   start\t     end\t  random;\t Shuffle,\t    Sort\n",N);
			System.out.print("Array List");
		}
		if( line == 2)
			System.out.print("Linked Array");
		for(int i=0;i<times.length;i++)
			System.out.printf("\t%8d",times[i]);
		System.out.printf("\n");

	}

	public static void printSetResults(long [] times, int N, int line){
		if(line==1){
			System.out.println("Set");
			System.out.printf("N = %d:\t us to\n\t\t     Add\t   Check\n",N);
			System.out.print("Hash Set");
		}
		if( line == 2)
			System.out.print("Tree Set");
		for(int i=0;i<times.length;i++)
			System.out.printf("\t%8d",times[i]);
		System.out.printf("\n");

	}
	
	public static long[] perfomanceArrayTest(List<Integer> list, int N){
		long beginT=0, endT=0;
		long[] times = new long[5];
		SecureRandom random = new SecureRandom();
		
		list.clear();
		beginT = System.nanoTime();
		for(int i=0;i<N;i++)
			list.add(0,random.nextInt(10*N)+1);
		endT = System.nanoTime();
		times[0]= endT-beginT;
		list.clear();

		beginT = System.nanoTime();
		for(int i=0;i<N;i++)	
			list.add(random.nextInt(10*N)+1);
		endT = System.nanoTime();
		times[1]= endT-beginT;
		list.clear();

		beginT = System.nanoTime();
		for(int i=0;i<N;i++)
			list.add(random.nextInt((list.size())+1),random.nextInt(10*N)+1);
		endT = System.nanoTime();
		times[2]= endT-beginT;

		beginT = System.nanoTime();
		Collections.shuffle(list);
		endT = System.nanoTime();
		times[3]= endT-beginT;

		beginT = System.nanoTime();
		list.sort(null);
		endT = System.nanoTime();
		times[4]= endT-beginT;

		for(int i=0;i<times.length;i++)
			times[i]/=1E3;

		return times;
	}
	
	public static long[] perfomanceSetTest(Set <Integer> set, int N){
		long beginT, endT;
		long[] times = new long[2];
		SecureRandom random = new SecureRandom();

		set.clear();
		beginT = System.nanoTime();
		for(int i=0;i<N;i++)
			set.add(random.nextInt(10*N)+1);
		endT = System.nanoTime();
		times[0]= endT-beginT;
		
		beginT = System.nanoTime();
		set.contains(random.nextInt(10*N)+1);
		endT = System.nanoTime();
		times[1]= endT-beginT;
		
		for(int i=0;i<times.length;i++)
			times[i]/=1E3;

		return times;
	}
}

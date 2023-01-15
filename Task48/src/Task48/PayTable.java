package Task48;

import java.text.NumberFormat;
import java.util.Scanner;

public class PayTable extends Salary implements Calculate, Output{
	
	public PayTable(String name, int pay, int family, int overtime) {
		super(name, pay, family, overtime);
	}
	
	public PayTable() {
	}

	public void title() {
		System.out.println("\t\t\t\t\t\t\t**** 급여명세서 ****");
		for(int i=0;i<140;i++) {
			System.out.print("-");}
		System.out.println("\n이름\t기본급\t\t가족수\t가족수당\t\t시간외근무"
				+"\t\t시간외수당\t\t세금\t\t인센티브(%)\t성과금\t\t실수령액");
		for(int i=0;i<140;i++) {
			System.out.print("-");}
	}
	
	public void footer() {
	    System.out.println("\n인원수: "+getCount()+"명");
	}
	
	@Override
	public void out() {
		NumberFormat formatter = NumberFormat.getNumberInstance();
		
		System.out.print("\n"+getName()+"\t"+formatter.format(getPay())+"원\t"+getFamily()+"\t");
		System.out.print(formatter.format(getFamily_p())+"원\t\t"+getOvertime()+"\t\t"+formatter.format(getOvertime_p())+"원\t\t"
		+formatter.format(getTax())+"원\t\t"+getIncentive()*100f+"\t\t"+formatter.format(getIncen_p())+"원\t"
		+formatter.format(getTotal_pay())+"원");
	}

	@Override
	public void calc() {
		if(getFamily()<3) {
			setFamily_p(getFamily()*20000);
		}else {
			setFamily_p(60000);
		}
		//setFamily_p( (getFamily()>=3)?60000:getFamily()*20000 ); ### ?오른쪾은 참일 때의 값, 콜론(:)오른쪽은 거짓일 때의 값.
		setOvertime_p(getOvertime()*5000);
		setTax((int)(getPay()*0.1));
		setIncen_p((int)(getIncentive()*getPay()));
		setTotal_pay((int)(getPay()+getFamily_p()+getIncen_p()+getOvertime_p()-getTax()));
	}
	
	public static void main(String[] args) {
		
		PayTable tt=new PayTable();
		Scanner scan=new Scanner(System.in);
		String con;
		
		PayTable []pt = new PayTable[3];
	    pt[0] = new PayTable("소서노",750000,2,3);
	    pt[1] = new PayTable("고주몽",800000,1,4);
	    pt[2] = new PayTable("모팔모",650000,6,2);
	    
	    float a;
	    
	    while(true) {
		    tt.title();
		    for(int i=0;i<pt.length;i++) {
			    pt[i].calc();
			    pt[i].out();
		    }
		    setCount(pt.length);
		    tt.footer();
		    
		    System.out.println("\n계속하려면 아무 키나 입력하세요...");
		    con=scan.next();
		    
		    do {
		    	System.out.print("\n인센티브(%)를 입력하세요: ");
			    try {
			    a=Float.parseFloat(scan.next());
			    setIncentive(a*0.01f);
			    }catch(Exception e){
			    	a=-1;
			    	System.out.println("잘못된 입력. 정수를 입력하세요.");
			    }
		    }while(a<0);
		    
	    }

	}

}

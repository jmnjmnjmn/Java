package subjects;

import observers.IObserver;


interface ISubject {
	void registerObserver(IObserver o);
//	void registerObserver(String eventName, IObserver o);
	
	void removeObserver(IObserver o);
//  void removeObserver((String eventName, IObserver o);//¶à¸öevent listener
	
	void notifyObservers();
	//void notifyObservers(String eventName);
}

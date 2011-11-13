//update nov.13.11 23:40

#include <iostream>
#include <vector>
#include <string>
using namespace std;

class Line 
{
public:
  string level;
  string timestamp;
  string message;
  Line()
  {
    this->level = "";
    this->timestamp = "";
    this->message = "";
  }
  Line(const Line *l)
  {
    this->level = l->level;
    this->timestamp = l->timestamp;
    this->message = l->message;
  }
};

class LineList
{
  public:
    Line* line;
    LineList* next;
    LineList()
    {
      this->line = NULL;
      this->next = NULL;
    }
    
    LineList* getCopy()
    {
      LineList* cpy = new LineList();
      cpy->line = new Line(this->line);
      if (this->next != NULL)
	cpy->next = this->next->getCopy();
      return cpy;
    }
};

int main();
// Merges two lists
// Receives resultList and mergedList - sorted linked lists
// Merges mergedList into resultList
// Returns a pointer to the head of the merged list
LineList* mergeLists(LineList* resultList, LineList* mergedList);
// Receive a line with comma, and construct a Line with 3 fields
// This function allocates memory.
Line* parseLine(string line);
// Frees all memory allocated for the list
void deleteList(LineList* l);
// Return true if the line starts with "#" indicating a new log file is starting
bool isNewFile(string line);
// Add a new line to an existing list of lines (at the end)
// This function allocates memory
// pay attention to adding the first element to an empty list.
LineList* addOneLine(LineList* l, Line* newLine);
// Print all the lines contained in the list
void printLines(LineList* l);
//returns true if a is later then b
bool isLater(Line* a, Line* b);
//returns true if LineList is empty
bool isEmpty(LineList* l);

LineList* mergeListsOld(LineList* resultList , LineList* mergedList)
{
  
  if (isEmpty(mergedList) )
    return resultList;
  if (isEmpty(resultList) )
  {
    resultList=new LineList();
    resultList->line=new Line(mergedList->line);
    mergedList=mergedList->next;
  }
  LineList *iterator = resultList;
  cout<<"lists not empty"<<endl;
  while (iterator->next != NULL && mergedList != NULL)
  {
      cout<<"checks same value"<<endl;
      cout<<"next: "<<iterator->next<<endl;
      cout<<" this->timestamp: "<<iterator->line<<endl;
      cout<<" next->timestamp: "<<iterator->next->line->timestamp<<endl;
    while( iterator->next != NULL && iterator->line->timestamp == iterator->next->line->timestamp)
    {
      cout<<"is the same"<<endl;

      iterator = iterator->next;
    }
    cout<<"node found"<<endl;

    bool b = isLater(iterator->line , mergedList->line);
    if (b == true)
    {
      LineList* tmp = new LineList();
      
      tmp->line = new Line(mergedList->line);
      tmp->next = iterator->next;
      iterator->next = tmp;
      iterator = iterator->next;
      mergedList = mergedList->next;      
    }
  }
  
  //return the resultList if we finisht the Lines in the mergedList
  if(mergedList== NULL)
    return resultList;
 

  //copy the rest of the mergedList if we at the end of the resultList
  
  while(mergedList != NULL)
  {
    LineList* rest = new LineList();
    rest->line = new Line(mergedList->line);
    iterator->next = rest;
    iterator = iterator->next;
    mergedList = mergedList->next;
    
  }
  
  //the next of the end of the resultList will point to NULL;
  iterator->next = NULL;
  return resultList;

}

void insertCopy(LineList *prev , LineList *next)
{
  LineList *tmp = prev->next;
  LineList *nextCopy = new LineList();
  nextCopy->line = new Line(next->line);
  prev->next = nextCopy;
  nextCopy->next = tmp;
}



LineList* mergeLists(LineList* resultList , LineList* mergedList)
{
    
    LineList *merIterator = mergedList;
    LineList *resIterator = resultList;
    LineList *prevResIterator = NULL;
    if (resultList == NULL && mergedList == NULL)
      return NULL;
    if(resultList == NULL)
    {
      return mergedList->getCopy();
    }

    while (resIterator != NULL && merIterator != NULL)
    {
      cout<<"status res: "<<endl;
      //printLines(resultList);
      
      if(isLater(resIterator->line , merIterator->line))
      {
	if(prevResIterator != NULL)
	{
	  insertCopy(prevResIterator, merIterator);
	  prevResIterator = prevResIterator->next;
	}
	 else
	{
	  prevResIterator=new LineList();
	  prevResIterator->line=new Line(merIterator->line);
	  prevResIterator->next=resIterator;
	}
	merIterator = merIterator->next;
      }
      else
      {
	prevResIterator = resIterator;
	resIterator = resIterator->next;
      }
    }
    if(merIterator!=NULL)
      prevResIterator->next = merIterator->getCopy();
    return resultList;
}


Line* parseLine(string line)
{
  Line* parse = new Line();
  vector<string> victor;
  int i;
  string str="";
  for(i=0 ; i<line.length() ; i++)
  {
    if (line[i] !=',')
      str = str+line[i];
    else
    {
      victor.push_back(str);
      str = "";
    }
  }
  victor.push_back(str);
  
  parse->level = victor[0];
  parse->timestamp = victor[1];
  parse->message = victor[2];
 
  return parse;
}


void printLines(LineList* l)
{
  LineList* iterator = l;
  while (iterator != NULL)
  {
    cout<< iterator->line->level <<","<< iterator->line->timestamp <<","<<iterator->line->message<< endl;
    iterator = iterator->next;
  }
}

bool isLater(Line* a, Line* b)
{
  int i;
  cout<<"checking if "<<a->timestamp<<">"<<b->timestamp<<"...";
  for ( i = 0; i<a->timestamp.length() ; i++)
  {
    if(a->timestamp[i] > b->timestamp[i])
    {
      cout<<"yes"<<endl;
      return true;
    }
    else if(a->timestamp[i] < b->timestamp[i])
    {
      cout<<"NOPE!"<<endl;
      return false;
    }
  }
  cout<<"NOP"<<endl;
  return false;
}

bool isEmpty(LineList* l)
{
  return (l==NULL);
}

//******


// Add a new line to an existing list of lines (ats the end)
// This function allocates memory
// pay attention to adding the first element to an empty list.
LineList* addOneLine(LineList* l, Line* newLine)
{
  LineList *iterator=l;
  //if the list is empty we need to create the first node
  if (isEmpty(iterator))
  {
    iterator=new LineList();
    iterator->next=NULL;
    iterator->line=newLine;
    return iterator;
  }
  // otherwise we need to add a new node to the end of the list, so we will iterate to the end
  // and then add a new node there.
  else
  {
    while (iterator->next!=NULL)
      iterator=iterator->next;
    iterator->next=new LineList();
    iterator->next->next=NULL;
    iterator->next->line=newLine;
  }
  
    return l;
}
// Return true if the line starts with "#" indicating a new log file is starting
bool isNewFile(string line)
{
    return line[0]=='#';
}

void deleteList(LineList* l)
{
   LineList *current=l;
   LineList *next;
   // this loop iterates the list, and deletes all nodes memory
   while (current!=NULL)
   {
     next=current->next;
     delete current->line;
     delete current;
     current=next;
   }
}


int main()
{
    bool eof = false;
    string line;
    LineList *resultList=NULL, *mergedList=NULL;
    getline(cin, line);
        cout<<"line: "<<line.length()<<endl;
	if(line.length() == 0 || line[0] != '#')
	{
	  cout<<"ERROR"<<endl;
	  eof = true;
	}
	else
	{
	  while (!eof)
	  {	
		if(isNewFile(line))
		{
		  resultList= mergeLists(resultList , mergedList);
		  deleteList(mergedList);
		  mergedList=NULL;

		}
		else
		{
		  Line *l = parseLine(line);
		  mergedList= addOneLine(mergedList, l);
		}
		getline(cin, line);
		if (cin.eof())
		  eof = true;
		
	  }
	  cout<<"before const final merge"<<endl;
	  resultList = mergeLists(resultList , mergedList);
	  cout<<"after final merge"<<endl;

	  printLines(resultList);
	  deleteList(resultList);
	  deleteList(mergedList);
	}
}

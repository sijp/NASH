//Nadav Tzur 038087169
//Shlomi Israel 038070967

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
  //default constructor
  Line()
  {
    this->level = "";
    this->timestamp = "";
    this->message = "";
  }
  //constructor - constructs a new Line with the same data from l
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
    //default constructor
    LineList()
    {
      this->line = NULL;
      this->next = NULL;
    }
    
    //returns a (deep) copy of the list 
    
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
//inserts a copy of `next` after the `prev` node.
void insertCopy(LineList *prev , LineList *next);
//checks if `a` is later than `b`
bool isLater(Line* a, Line* b);



void insertCopy(LineList *prev , LineList *next)
{
  LineList *tmp = prev->next;
  LineList *nextCopy = new LineList();
  nextCopy->line = new Line(next->line);
  prev->next = nextCopy;
  nextCopy->next = tmp;
}

/*
 * mergeLists - the main idea, 
 * the function merge two lists, one is the resultlist untik now, and adds the elements from
 * the merged list in the proper place.
 * we iterate on both lists, and returning the first element on the resultlist.
 * if we put a new element before the first in thr resultlist, we change the first pointer as well.
 * 
 */

LineList* mergeLists(LineList* resultList , LineList* mergedList)
{
    
    LineList *merIterator = mergedList;
    LineList *resIterator = resultList;
    LineList *prevResIterator = NULL;
    //basic cases
    if (resultList == NULL && mergedList == NULL)
      return NULL;
    if(resultList == NULL)
    {
      return mergedList->getCopy();
    }
    //main loop
    while (resIterator != NULL && merIterator != NULL)
    {
      //checks if the resIterator is later than the merIterator
      if(isLater(resIterator->line , merIterator->line))
      {
	//checks if the iterator is in the beginning of the resultList
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
	  resultList = prevResIterator;
	}
	merIterator = merIterator->next;
      }
      else
      {
	prevResIterator = resIterator;
	resIterator = resIterator->next;
      }
    }
    //copy the tail of the mergedList to the end of the result list
    if(merIterator!=NULL)
      prevResIterator->next = merIterator->getCopy();
    return resultList;
}

//seperate the parts of `line` and returns a new Line object.
Line* parseLine(string line)
{
  Line* parse = new Line();
  vector<string> victor;
  unsigned int i=0;
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

//prints the lines
void printLines(LineList* l)
{
  LineList* iterator = l;
  while (iterator != NULL)
  {
    cout<< iterator->line->level <<","<< iterator->line->timestamp <<","<<iterator->line->message<< endl;
    iterator = iterator->next;
  }
}
//checks if a later then b , using character values 
bool isLater(Line* a, Line* b)
{
  unsigned int i;
  for ( i = 0; i<a->timestamp.length() ; i++)
  {
    if(a->timestamp[i] > b->timestamp[i])
    {
      return true;
    }
    else if(a->timestamp[i] < b->timestamp[i])
    {
      return false;
    }
  }
  return false;
}
//checks if the list is empty
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
    //checks if the filr starts with '#' or if the `line` in empty.
    if(line.length() == 0 || line[0] != '#')
    {
      cout<<"ERROR"<<endl;
      eof = true;
    }
    else
    {
      while (!eof)
      {	
	    //if we encounter a new file, than we need to merge the two lists. and afterwards deletes the entire `mergeList`
	    if(isNewFile(line))
	    {
	      resultList= mergeLists(resultList , mergedList);
	      deleteList(mergedList);
	      mergedList=NULL;

	    }
	    //otherwise we simply add a line to `mergeList`
	    else
	    {
	      Line *l = parseLine(line);
	      mergedList= addOneLine(mergedList, l);
	    }
	    getline(cin, line);
	    if (cin.eof())
	      eof = true;
	    
      }
      //merge one last time for the last created mergelist (there is no more '#')
      resultList = mergeLists(resultList , mergedList);

      //prints the output
      printLines(resultList);
      
      //frees all heap blocks.
      deleteList(resultList);
      deleteList(mergedList);
    }
    return 0;
}

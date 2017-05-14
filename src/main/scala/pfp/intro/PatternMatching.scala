package pfp.intro

/**
  *  Second most widely used feature in Scala language
  *  At first glance looks like a switch statement on steroids, but it is so much more then that
  */
object PatternMatching {

  /** switch on steroids */
  def goToTheGym(): Boolean = true

  val action = goToTheGym() match {
    case true => "do it!"
    case false => "stay in bed"
  }

  def gym(month: String): Boolean = month match {
    case "January" => false
    case "February" => false
    case "March" => false
    case "April" => false
    case "May" => false
    case "June" => true
    case "July" => true
    case "August" => false
    case "September" => false
    case "October" => false
    case "November" => false
    case "December" => false
  }

  /** decomposing lists */
  def check(l: List[Int]) = l match {
    case List(3, 4) => "3, 4"
    case List(1) => "just 1"
    case 1 :: 2 :: Nil => "1, 2"
    case 1 :: 2 :: other => s"1, 2 and $other"
    case _ => "whatever"
  }

  /** Guards, guards! */
  def check2(l: List[Int]): String = l match {
    case ints if(ints.size > 3) => "wow, more then three!"
    case ints => "meh, could be better"
  }

  /** decomposing arbitrary strutures */
  trait Place
  case object Headquarters extends Place
  case object Battlefield extends Place

  trait Soldier
  case class Robot(battery: Int) extends Soldier
  case class Human(life: Int) extends Soldier

  def sendTo(soldier: Soldier): Place = soldier match {
    case Human(life) => Headquarters
    case Robot(battery) if(battery < 10) => Headquarters
    case Robot(battery)  => Battlefield
  }

  /**
    * Patterns, patterns everywhere!
    */
  // method
  def aMrthod(argument: Int): Int = argument match {
    case 1 => 0
    case n => n
  }
  // value definitions
  case class Employee(firstName: String, lastName: String)
  case class Project(manager: Employee, employees: List[Employee])

  val project = Project(Employee("John", "Smith"), List.empty)
  val lastName_ = project.manager.lastName
  val Project(Employee(_, lastName), _) = project

  // lambdas
  val list = List((1,0,1), (0,0,1), (0,0,0), (1,1,1))
  val zeroInMiddleEnding1_ = list.filter(v => v._2 == 0 && v._3 == 1)

  val zeroInMiddleEnding1 = list.filter {
    case (_, 0, _) 	=> true
    case _ 		=> false
  }
  
}

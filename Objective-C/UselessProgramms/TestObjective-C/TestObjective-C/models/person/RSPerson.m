//
//  NSObject+RSPerson.m
//  TestObjective-C
//
//  Created by Матвей Попов on 17.05.2021.
//

#import "RSPerson.h"

@implementation RSPerson

-(void)sayHello {
    NSLog(@"\nHello, my name is %@\n", _name);
}

-(instancetype)initWithName:(NSString *)name {
    self = [super init];
    if (self) {
        _name = name;
    }
    return self;
}

@end

//
//  RSPassenger.m
//  TestObjective-C
//
//  Created by Матвей Попов on 17.05.2021.
//

#import "RSPassenger.h"

@implementation RSPassenger

@synthesize identifier = _identifier;

- (instancetype)initWithName:(NSString *)name {
    self = [self init];
    if (self) {
        _name = name;
        _identifier = [[NSUUID UUID] UUIDString];
    }
    return self;
}

- (void)sayHello {
    NSLog(@"Hello dear, %@", self.name);
}

@end
